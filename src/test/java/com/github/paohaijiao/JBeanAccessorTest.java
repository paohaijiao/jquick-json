package com.github.paohaijiao;

import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.mapper.JBeanAccessor;
import com.github.paohaijiao.mapper.JBeanAccessorFactory;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.serializer.JSONSerializer;
import com.github.paohaijiao.util.JReflectionUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 验证 jquick-asm 生成的字节码 Bean 访问器：
 * 1. 满足条件的 bean 走字节码路径，不满足的走反射兜底；
 * 2. 序列化/反序列化结果与旧反射实现一致；
 * 3. 修复旧实现中 null 字段值导致 NPE 的问题；
 * 4. 粗粒度性能对比。
 */
public class JBeanAccessorTest {

    private static final String GEN_PACKAGE_PREFIX = "com.github.paohaijiao.mapper.gen.JBeanAccessor_";

    private static void setNoAccessorField(Object bean, String name, Object value) throws Exception {
        Field field = bean.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(bean, value);
    }

    private static Object getNoAccessorField(Object bean, String name) throws Exception {
        Field field = bean.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(bean);
    }

    @Test
    public void testBytecodePathUsed() {
        JBeanAccessor accessor = JBeanAccessorFactory.get(SimpleBean.class);
        assertNotNull(accessor);
        assertTrue("SimpleBean 应走 jquick-asm 字节码路径, 实际为 " + accessor.getClass().getName(), accessor.getClass().getName().startsWith(GEN_PACKAGE_PREFIX));
    }

    @Test
    public void testReflectionFallbackUsed() {
        JBeanAccessor accessor = JBeanAccessorFactory.get(NoAccessorBean.class);
        assertNotNull(accessor);
        assertEquals(com.github.paohaijiao.mapper.JReflectionBeanAccessor.class, accessor.getClass());
    }

    @Test
    public void testRoundTrip() {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        SimpleBean bean = new SimpleBean("Martin", 30, 7, true, 123456789L);
        String json = serializer.serialize(bean);
        assertNotNull(json);
        SimpleBean back = serializer.deserialize(json, SimpleBean.class);
        assertNotNull(back);
        assertEquals(bean.getName(), back.getName());
        assertEquals(bean.getAge(), back.getAge());
        assertEquals(bean.getCount(), back.getCount());
        assertEquals(bean.isActive(), back.isActive());
        assertEquals(bean.getTotal(), back.getTotal());
    }

    @Test
    public void testNullFieldNoNpe() {
        SimpleBean bean = new SimpleBean("Martin", null, 7, true, 123456789L);
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String json = serializer.serialize(bean);
        assertTrue(json.contains("\"age\":null"));
        SimpleBean back = serializer.deserialize(json, SimpleBean.class);
        assertEquals("Martin", back.getName());
        assertNull(back.getAge());
    }

    @Test
    public void testNestedBeanRoundTrip() {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        com.github.paohaijiao.model.JProduct jProduct = new com.github.paohaijiao.model.JProduct();
        com.github.paohaijiao.model.JProduct.ProductDTO dto = new com.github.paohaijiao.model.JProduct.ProductDTO();
        dto.setId(1001);
        dto.setSerialNumber(20230501123456789L);
        dto.setIsAvailable(true);
        dto.setPrice(199.99);
        jProduct.setProduct(dto);

        String json = serializer.serialize(jProduct);
        com.github.paohaijiao.model.JProduct back = serializer.deserialize(json,
                com.github.paohaijiao.model.JProduct.class);
        assertEquals(1001, back.getProduct().getId().intValue());
        assertEquals(20230501123456789L, back.getProduct().getSerialNumber().longValue());
        assertEquals(true, back.getProduct().getIsAvailable());
        assertEquals(199.99, back.getProduct().getPrice(), 0.0001);
    }

    @Test
    public void testFallbackRoundTrip() throws Exception {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();

        NoAccessorBean bean = new NoAccessorBean();
        setNoAccessorField(bean, "name", "fallback");
        setNoAccessorField(bean, "age", 25);

        String json = serializer.serialize(bean);
        assertTrue(json.contains("\"name\":\"fallback\""));
        assertTrue(json.contains("\"age\":25"));

        NoAccessorBean back = serializer.deserialize(json, NoAccessorBean.class);
        assertEquals("fallback", getNoAccessorField(back, "name"));
        assertEquals(25, getNoAccessorField(back, "age"));
    }

    @Test
    public void testPerformanceSmoke() throws Exception {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        SimpleBean bean = new SimpleBean("Martin", 30, 7, true, 123456789L);
        for (int i = 0; i < 1000; i++) {
            serializer.serialize(bean);
        }
        String json = serializer.serialize(bean);
        int iterations = 20000;
        long t0 = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            new JSONObject().fromBean(bean);
        }
        long asmNanos = System.nanoTime() - t0;
        t0 = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            serializer.deserialize(json, SimpleBean.class);
        }
        long asmDeserNanos = System.nanoTime() - t0;
        List<Field> fields = JReflectionUtils.getAllFields(bean.getClass());
        t0 = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            JSONObject jsonObj = new JSONObject();
            for (Field field : fields) {
                field.setAccessible(true);
                jsonObj.put(field.getName(), field.get(bean));
            }
        }
        long reflectionNanos = System.nanoTime() - t0;
        System.out.printf("fromBean 反射: %,d ns | fromBean 字节码: %,d ns | 加速比: %.1fx%n", reflectionNanos, asmNanos, (double) reflectionNanos / asmNanos);
        System.out.printf("deserialize 字节码: %,d ns / %d 次%n", asmDeserNanos, iterations);
        assertTrue(asmNanos > 0);
        assertTrue(reflectionNanos > 0);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleBean {
        private String name;
        private Integer age;
        private int count;
        private boolean active;
        private long total;
    }

    /**
     * 无 getter/setter 的 bean，用于验证反射兜底路径
     */
    public static class NoAccessorBean {

        private String name;

        private Integer age;

        public NoAccessorBean() {
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setAge(Integer age) {
            this.age = age;
        }
    }
}

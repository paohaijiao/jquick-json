package com.github.paohaijiao;

import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.mapper.JBeanAccessor;
import com.github.paohaijiao.mapper.JBeanAccessorFactory;
import com.github.paohaijiao.mapper.JBeanFieldMeta;
import com.github.paohaijiao.mapper.JReflectionBeanAccessor;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONBaseObject;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.serializer.JSONSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * jquick-asm 字节码 Bean 访问器 vs 反射实现的基准测试。
 */
public class JBeanAccessorBenchmarkTest {

    /** 消费每次操作的结果，防止 JIT 将循环体优化为空操作 */
    private long sink;

    @Test
    public void benchmark() throws Exception {
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        SimpleBean bean = new SimpleBean("Martin", 30, 7, true, 123456789L);
        String json = serializer.serialize(bean);
        assertNotNull(json);
        JBeanAccessor bytecode = JBeanAccessorFactory.get(SimpleBean.class);
        assertTrue(bytecode.getClass().getName().startsWith("com.github.paohaijiao.mapper.gen."));
        Field[] javaFields = SimpleBean.class.getDeclaredFields();
        JBeanFieldMeta[] metas = bytecode.fields();
        JBeanAccessor reflection = new JReflectionBeanAccessor(SimpleBean.class, metas, javaFields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Martin");
        jsonObject.put("age", 30);
        jsonObject.put("count", 7);
        jsonObject.put("active", true);
        jsonObject.put("total", 123456789L);

        int fieldCount = metas.length;

        long getRefl = benchmark(() -> {
            for (int i = 0; i < fieldCount; i++) {
                Object v = reflection.get(bean, i);
                sink += v == null ? 0 : v.hashCode();
            }
        }, 20_000, 200_000, 5);
        long getByte = benchmark(() -> {
            for (int i = 0; i < fieldCount; i++) {
                Object v = bytecode.get(bean, i);
                sink += v == null ? 0 : v.hashCode();
            }
        }, 20_000, 200_000, 5);

        Object[] values = {"x", 1, 1, true, 1L};
        long setRefl = benchmark(() -> {
            for (int i = 0; i < fieldCount; i++) {
                reflection.set(bean, i, values[i]);
            }
        }, 20_000, 200_000, 5);
        long setByte = benchmark(() -> {
            for (int i = 0; i < fieldCount; i++) {
                bytecode.set(bean, i, values[i]);
            }
        }, 20_000, 200_000, 5);

        long createRefl = benchmark(() -> sink += reflection.create().hashCode(), 20_000, 100_000, 5);
        long createByte = benchmark(() -> sink += bytecode.create().hashCode(), 20_000, 100_000, 5);

        long fromRefl = benchmark(() -> sink += fromBeanWith(reflection, bean).size(), 10_000, 50_000, 5);
        long fromByte = benchmark(() -> sink += fromBeanWith(bytecode, bean).size(), 10_000, 50_000, 5);

        long toRefl = benchmark(() -> sink += toBeanWith(reflection, jsonObject, SimpleBean.class).getName().length(),
                10_000, 50_000, 5);
        long toByte = benchmark(() -> sink += toBeanWith(bytecode, jsonObject, SimpleBean.class).getName().length(),
                10_000, 50_000, 5);

        long serByte = benchmark(() -> sink += serializer.serialize(bean).length(), 10_000, 50_000, 5);
        long desByte = benchmark(() -> sink += serializer.deserialize(json, SimpleBean.class).getName().length(),
                2_000, 5_000, 3);

        System.out.println();
        System.out.println("===== jquick-json Bean Accessor Benchmark (jquick-asm bytecode vs reflection) =====");
        System.out.println("机器环境: " + System.getProperty("os.name") + " / " + System.getProperty("java.vm.name")
                + " " + System.getProperty("java.version"));
        System.out.printf("%-18s | %16s | %16s | %8s%n", "场景", "反射 ns/op", "字节码 ns/op", "加速比");
        System.out.printf("%-18s | %16.1f | %16.1f | %7.2fx%n", "accessor.get", perOp(getRefl, 200_000L * fieldCount), perOp(getByte, 200_000L * fieldCount), speedup(getRefl, getByte));
        System.out.printf("%-18s | %16.1f | %16.1f | %7.2fx%n", "accessor.set", perOp(setRefl, 200_000L * fieldCount), perOp(setByte, 200_000L * fieldCount), speedup(setRefl, setByte));
        System.out.printf("%-18s | %16.1f | %16.1f | %7.2fx%n", "accessor.create", perOp(createRefl, 100_000), perOp(createByte, 100_000), speedup(createRefl, createByte));
        System.out.printf("%-18s | %16.1f | %16.1f | %7.2fx%n", "fromBean", perOp(fromRefl, 50_000), perOp(fromByte, 50_000), speedup(fromRefl, fromByte));
        System.out.printf("%-18s | %16.1f | %16.1f | %7.2fx%n", "toBean", perOp(toRefl, 50_000), perOp(toByte, 50_000), speedup(toRefl, toByte));
        System.out.println("----- 完整链路（均基于字节码访问器） -----");
        System.out.printf("%-18s | %16s | %16.1f | %12s%n", "serialize", "-", perOp(serByte, 50_000), "-");
        System.out.printf("%-18s | %16s | %16.1f | %12s%n", "deserialize", "-", perOp(desByte, 5_000), "-");

        for (int i = 0; i < fieldCount; i++) {
            assertEquals(reflection.get(bean, i), bytecode.get(bean, i));
        }
        assertTrue(getByte > 0 && fromByte > 0 && desByte > 0);
    }

    /** 与 {@link JSONObject#fromBean(Object)} 相同的转换逻辑，字段读走指定 accessor（公平对比） */
    private JSONObject fromBeanWith(JBeanAccessor acc, Object bean) {
        JBeanFieldMeta[] metas = acc.fields();
        JSONObject out = new JSONObject();
        for (int i = 0; i < metas.length; i++) {
            JBeanFieldMeta meta = metas[i];
            if (meta.ignored()) {
                continue;
            }
            Object value = acc.get(bean, i);
            String fieldName = meta.jsonName();
            if (value == null) {
                out.put(fieldName, null);
                continue;
            }
            if (value instanceof Map) {
                out.put(fieldName, new JSONObject((Map) value));
            } else if (value instanceof Collection) {
                out.put(fieldName, new JSONArray(JSONBaseObject.convertToList(value)));
            } else if (value instanceof Number || value instanceof Boolean || value instanceof String) {
                out.put(fieldName, value);
            } else {
                out.put(fieldName, value.toString());
            }
        }
        return out;
    }

    /** 与 {@link JSONObject#toBean(Class)} 相同的转换逻辑，字段写走指定 accessor（公平对比） */
    private <T> T toBeanWith(JBeanAccessor acc, JSONObject json, Class<T> clazz) {
        JBeanFieldMeta[] metas = acc.fields();
        T instance = (T) acc.create();
        for (int i = 0; i < metas.length; i++) {
            JBeanFieldMeta meta = metas[i];
            if (meta.ignored()) {
                continue;
            }
            String fieldName = meta.fieldName();
            if (!json.containsKey(fieldName)) {
                continue;
            }
            Object value = json.get(fieldName);
            if (value != null && !meta.type().isAssignableFrom(value.getClass())) {
                value = json.getNativeValue(fieldName, meta.type(), value);
            }
            acc.set(instance, i, value);
        }
        return instance;
    }

    /** 执行基准：预热 warmup 次，采样 rounds 轮（每轮 iterations 次），返回最优轮总耗时(ns) */
    private long benchmark(Runnable task, int warmup, int iterations, int rounds) {
        for (int i = 0; i < warmup; i++) {
            task.run();
        }
        long best = Long.MAX_VALUE;
        for (int r = 0; r < rounds; r++) {
            long t0 = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                task.run();
            }
            long cost = System.nanoTime() - t0;
            if (cost < best) {
                best = cost;
            }
        }
        return best;
    }

    private static double perOp(long totalNanos, long iterations) {
        return totalNanos / (double) iterations;
    }

    private static double speedup(long reflectionNanos, long bytecodeNanos) {
        return reflectionNanos / (double) bytecodeNanos;
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
}

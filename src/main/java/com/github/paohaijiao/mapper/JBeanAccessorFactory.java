/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.mapper;

import com.github.paohaijiao.anno.JSONField;
import com.github.paohaijiao.anno.JSONIgnore;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.util.JReflectionUtils;
import com.jquick.asm.util.JQuickBytecodeUtil;
import com.jquick.asm.util.JQuickTypeUtil;
import com.jquick.asm.writer.JQuickClassWriterTool;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bean 访问器工厂：使用 jquick-asm 为每个 bean 类型动态生成字节码访问器，
 */
public final class JBeanAccessorFactory {

    static JConsole jConsole=new JConsole();

    private static final String GEN_PACKAGE = "com.github.paohaijiao.mapper.gen";

    private static final String META_FIELD = "metaFields";

    private static final String META_INTERNAL = "com/github/paohaijiao/mapper/JBeanFieldMeta";

    private static final String META_ARRAY_DESC = "[Lcom/github/paohaijiao/mapper/JBeanFieldMeta;";

    private static final ConcurrentHashMap<Class<?>, JBeanAccessor> CACHE = new ConcurrentHashMap<>();

    private static final AtomicInteger SEQ = new AtomicInteger();

    private JBeanAccessorFactory() {
    }

    /**
     * 获取指定类型的 Bean 访问器（缓存）。
     */
    public static JBeanAccessor get(Class<?> beanClass) {
        if (beanClass == null) {
            throw new IllegalArgumentException("beanClass must not be null");
        }
        JBeanAccessor accessor = CACHE.get(beanClass);
        if (accessor == null) {
            accessor = create(beanClass);
            JBeanAccessor prev = CACHE.putIfAbsent(beanClass, accessor);
            if (prev != null) {
                accessor = prev;
            }
        }
        return accessor;
    }

    private static JBeanAccessor create(Class<?> beanClass) {

        List<Field> javaFields = collectFields(beanClass);

        Method[] methods = beanClass.getMethods();

        List<JBeanFieldMeta> metas = new ArrayList<>(javaFields.size());

        List<Method> getters = new ArrayList<>(javaFields.size());

        List<Method> setters = new ArrayList<>(javaFields.size());

        boolean bytecodeSafe = !beanClass.isInterface() && !Modifier.isAbstract(beanClass.getModifiers()) && hasPublicNoArgConstructor(beanClass);
        for (Field field : javaFields) {
            JSONIgnore ignore = field.getAnnotation(JSONIgnore.class);
            JSONField jsonField = field.getAnnotation(JSONField.class);
            String jsonFieldName = jsonField != null ? jsonField.name() : null;
            String format = jsonField != null && jsonField.format() != null && !jsonField.format().isEmpty() ? jsonField.format() : null;
            boolean ignored = ignore != null || Modifier.isStatic(field.getModifiers());
            Class<?> fieldType = field.getType();
            if (fieldType.isPrimitive()) {
                fieldType = wrapperOf(fieldType);
            }
            metas.add(new JBeanFieldMeta(field.getName(), jsonFieldName, format, ignored, fieldType));
            Method getter = null;
            Method setter = null;
            if (!ignored && bytecodeSafe) {
                getter = findGetter(methods, field.getName());
                setter = findSetter(methods, field.getName(), field.getType());
                if (getter == null || setter == null) {
                    bytecodeSafe = false;
                }
            }
            getters.add(getter);
            setters.add(setter);
        }

        if (bytecodeSafe) {
            try {
                return generate(beanClass, metas, getters, setters);
            } catch (RuntimeException | LinkageError e) {
                String string="[JBeanAccessorFactory] bytecode generation failed for " + beanClass.getName() + ", fallback to reflection: " + e.getMessage();
                jConsole.error(string);
            }
        }
        Field[] fieldArray = javaFields.toArray(new Field[0]);
        return new JReflectionBeanAccessor(beanClass, metas.toArray(new JBeanFieldMeta[0]), fieldArray);
    }

    private static boolean hasPublicNoArgConstructor(Class<?> beanClass) {
        try {
            beanClass.getConstructor();
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * 收集类层级上的所有字段，顺序与 {@link JReflectionUtils#getAllFields(Class)} 一致：
     * 子类字段在前，父类字段在后（不包含 Object）。
     */
    private static List<Field> collectFields(Class<?> beanClass) {
        List<Field> result = new ArrayList<>();
        for (Class<?> c = beanClass; c != null && c != Object.class; c = c.getSuperclass()) {
            Collections.addAll(result, c.getDeclaredFields());
        }
        return result;
    }


    private static Method findGetter(Method[] methods, String fieldName) {
        String cap = capitalize(fieldName);
        String getName = "get" + cap;
        String isName = "is" + cap;
        for (Method m : methods) {
            if (m.getDeclaringClass().isInterface() || m.getParameterCount() != 0) {
                continue;
            }
            String name = m.getName();
            if (name.equals(getName) && m.getReturnType() != void.class) {
                return m;
            }
            if (name.equals(isName)) {
                Class<?> ret = m.getReturnType();
                if (ret == boolean.class || ret == Boolean.class) {
                    return m;
                }
            }
        }
        return null;
    }

    private static Method findSetter(Method[] methods, String fieldName, Class<?> fieldType) {
        String setName = "set" + capitalize(fieldName);
        for (Method m : methods) {
            if (m.getDeclaringClass().isInterface() || !m.getName().equals(setName) || m.getParameterCount() != 1) {
                continue;
            }
            Class<?> param = m.getParameterTypes()[0];
            if (param == fieldType || param.isAssignableFrom(fieldType)) {
                return m;
            }
            if (isPrimitiveWrapperPair(param, fieldType) || isPrimitiveWrapperPair(fieldType, param)) {
                return m;
            }
        }
        return null;
    }

    private static boolean isPrimitiveWrapperPair(Class<?> a, Class<?> b) {
        if (!a.isPrimitive()) {
            return false;
        }
        return wrapperOf(a) == b;
    }

    private static Class<?> wrapperOf(Class<?> primitive) {
        if (primitive == boolean.class) return Boolean.class;
        if (primitive == byte.class) return Byte.class;
        if (primitive == char.class) return Character.class;
        if (primitive == short.class) return Short.class;
        if (primitive == int.class) return Integer.class;
        if (primitive == long.class) return Long.class;
        if (primitive == float.class) return Float.class;
        if (primitive == double.class) return Double.class;
        throw new IllegalArgumentException("not a primitive: " + primitive);
    }

    private static String capitalize(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        char c = name.charAt(0);
        return (c >= 'a' && c <= 'z') ? (char) (c - 32) + name.substring(1) : name;
    }


    private static JBeanAccessor generate(Class<?> beanClass, List<JBeanFieldMeta> metas, List<Method> getters, List<Method> setters) {
        String genName = GEN_PACKAGE + ".JBeanAccessor_" + Integer.toHexString(beanClass.getName().hashCode()) + "_" + SEQ.incrementAndGet();
        String genInternal = genName.replace('.', '/');
        String beanInternal = JQuickTypeUtil.toInternalName(beanClass);
        byte[] bytes = JQuickClassWriterTool.builder(genName)
                .version(Opcodes.V1_8)
                .access(Opcodes.ACC_PUBLIC)
                .implementInterface(JBeanAccessor.class)
                .addField(Opcodes.ACC_PRIVATE, META_FIELD, META_ARRAY_DESC)
                .addMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", mv -> emitInit(mv, genInternal, metas))
                .addMethod(Opcodes.ACC_PUBLIC, "create", "()Ljava/lang/Object;", mv -> emitCreate(mv, beanInternal))
                .addMethod(Opcodes.ACC_PUBLIC, "fields", "()" + META_ARRAY_DESC, mv -> emitFields(mv, genInternal))
                .addMethod(Opcodes.ACC_PUBLIC, "get", "(Ljava/lang/Object;I)Ljava/lang/Object;", mv -> emitGet(mv, genInternal, beanInternal, metas, getters))
                .addMethod(Opcodes.ACC_PUBLIC, "set", "(Ljava/lang/Object;ILjava/lang/Object;)V", mv -> emitSet(mv, genInternal, beanInternal, metas, setters))
                .build();

        Class<?> clazz = JQuickBytecodeUtil.defineClass(genName, bytes);
        try {
            return (JBeanAccessor) clazz.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("failed to instantiate generated accessor " + genName, e);
        }
    }

    private static void emitInit(MethodVisitor mv, String genInternal, List<JBeanFieldMeta> metas) {
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        pushInt(mv, metas.size());
        mv.visitTypeInsn(Opcodes.ANEWARRAY, META_INTERNAL);
        for (int i = 0; i < metas.size(); i++) {
            JBeanFieldMeta meta = metas.get(i);
            mv.visitInsn(Opcodes.DUP);
            pushInt(mv, i);
            mv.visitTypeInsn(Opcodes.NEW, META_INTERNAL);
            mv.visitInsn(Opcodes.DUP);
            ldcOrNull(mv, meta.fieldName());
            ldcOrNull(mv, meta.jsonFieldName());
            ldcOrNull(mv, meta.format());
            mv.visitInsn(meta.ignored() ? Opcodes.ICONST_1 : Opcodes.ICONST_0);
            mv.visitLdcInsn(Type.getType(meta.type()));
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, META_INTERNAL, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Class;)V", false);
            mv.visitInsn(Opcodes.AASTORE);
        }
        mv.visitFieldInsn(Opcodes.PUTFIELD, genInternal, META_FIELD, META_ARRAY_DESC);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(12, 1);
        mv.visitEnd();
    }

    private static void emitCreate(MethodVisitor mv, String beanInternal) {
        mv.visitCode();
        mv.visitTypeInsn(Opcodes.NEW, beanInternal);
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, beanInternal, "<init>", "()V", false);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }

    private static void emitFields(MethodVisitor mv, String genInternal) {
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(Opcodes.GETFIELD, genInternal, META_FIELD, META_ARRAY_DESC);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private static void emitGet(MethodVisitor mv, String genInternal, String beanInternal,
                                List<JBeanFieldMeta> metas, List<Method> getters) {
        mv.visitCode();
        for (int i = 0; i < metas.size(); i++) {
            Method getter = getters.get(i);
            if (getter == null) {
                continue;
            }
            Label next = new Label();
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            pushInt(mv, i);
            mv.visitJumpInsn(Opcodes.IF_ICMPNE, next);
            emitGetterCall(mv, beanInternal, getter);
            mv.visitLabel(next);
            mv.visitFrame(Opcodes.F_NEW, 3,
                    new Object[]{genInternal, "java/lang/Object", Opcodes.INTEGER}, 0, null);
        }
        mv.visitInsn(Opcodes.ACONST_NULL);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();
    }

    private static void emitGetterCall(MethodVisitor mv, String beanInternal, Method getter) {
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitTypeInsn(Opcodes.CHECKCAST, beanInternal);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, beanInternal, getter.getName(), "()" + JQuickTypeUtil.toDescriptor(getter.getReturnType()), false);
        Class<?> ret = getter.getReturnType();
        if (ret.isPrimitive()) {
            String wrapper = JQuickTypeUtil.toInternalName(wrapperOf(ret));
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, wrapper, "valueOf",
                    "(" + JQuickTypeUtil.toDescriptor(ret) + ")L" + wrapper + ";", false);
        }
        mv.visitInsn(Opcodes.ARETURN);
    }

    private static void emitSet(MethodVisitor mv, String genInternal, String beanInternal, List<JBeanFieldMeta> metas, List<Method> setters) {
        mv.visitCode();
        for (int i = 0; i < metas.size(); i++) {
            Method setter = setters.get(i);
            if (setter == null) {
                continue;
            }
            Label next = new Label();
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            pushInt(mv, i);
            mv.visitJumpInsn(Opcodes.IF_ICMPNE, next);
            emitSetterCall(mv, beanInternal, setter);
            mv.visitLabel(next);
            mv.visitFrame(Opcodes.F_NEW, 4,
                    new Object[]{genInternal, "java/lang/Object", Opcodes.INTEGER, "java/lang/Object"}, 0, null);
        }
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(3, 4);
        mv.visitEnd();
    }

    private static void emitSetterCall(MethodVisitor mv, String beanInternal, Method setter) {
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitTypeInsn(Opcodes.CHECKCAST, beanInternal);
        mv.visitVarInsn(Opcodes.ALOAD, 3);
        Class<?> param = setter.getParameterTypes()[0];
        String paramDesc = JQuickTypeUtil.toDescriptor(param);
        if (param.isPrimitive()) {
            String wrapper = JQuickTypeUtil.toInternalName(wrapperOf(param));
            mv.visitTypeInsn(Opcodes.CHECKCAST, wrapper);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, wrapper, param.getName() + "Value", "()" + paramDesc, false);
        } else {
            mv.visitTypeInsn(Opcodes.CHECKCAST, JQuickTypeUtil.toInternalName(param));
        }
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, beanInternal, setter.getName(), "(" + paramDesc + ")V", false);
        mv.visitInsn(Opcodes.RETURN);
    }


    private static void pushInt(MethodVisitor mv, int value) {
        if (value >= 0 && value <= 5) {
            mv.visitInsn(Opcodes.ICONST_0 + value);
        } else if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.BIPUSH, value);
        } else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.SIPUSH, value);
        } else {
            mv.visitLdcInsn(value);
        }
    }

    private static void ldcOrNull(MethodVisitor mv, String value) {
        if (value == null) {
            mv.visitInsn(Opcodes.ACONST_NULL);
        } else {
            mv.visitLdcInsn(value);
        }
    }
}

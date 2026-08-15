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

import com.github.paohaijiao.util.JReflectionUtils;

import java.lang.reflect.Field;

/**
 * 反射实现的 Bean 访问器兜底实现。
 *
 * @author Martin
 * @since 2026/8/15
 */
public final class JReflectionBeanAccessor implements JBeanAccessor {

    private final Class<?> beanClass;

    private final JBeanFieldMeta[] fields;

    private final Field[] javaFields;

    public JReflectionBeanAccessor(Class<?> beanClass, JBeanFieldMeta[] fields, Field[] javaFields) {
        this.beanClass = beanClass;
        this.fields = fields;
        this.javaFields = javaFields;
        for (Field field : javaFields) {
            if (field != null) {
                field.setAccessible(true);
            }
        }
    }

    @Override
    public Object create() {
        return JReflectionUtils.newInstance(beanClass);
    }

    @Override
    public JBeanFieldMeta[] fields() {
        return fields;
    }

    @Override
    public Object get(Object bean, int index) {
        try {
            return javaFields[index].get(bean);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("failed to read field " + fields[index].fieldName() + " of " + beanClass.getName(), e);
        }
    }

    @Override
    public void set(Object bean, int index, Object value) {
        JReflectionUtils.setFieldValue(bean, fields[index].fieldName(), value);
    }
}

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

/**
 * FieldMeta容器。
 *
 * @author Martin
 * @since 2026/8/15
 */
public class JBeanFieldMeta {

    /**
     * Java 字段名
     */
    private final String fieldName;

    /**
     * jsonFieldName
     */
    private final String jsonFieldName;

    /**
     * format
     */
    private final String format;

    /**
     * 标注了 {@link com.github.paohaijiao.anno.JSONIgnore} 的字段
     */
    private final boolean ignored;

    /**
     * 字段声明类型
     */
    private final Class<?> type;

    public JBeanFieldMeta(String fieldName, String jsonFieldName, String format, boolean ignored, Class<?> type) {
        this.fieldName = fieldName;
        this.jsonFieldName = jsonFieldName;
        this.format = format;
        this.ignored = ignored;
        this.type = type;
    }

    public String fieldName() {
        return fieldName;
    }

    public String jsonFieldName() {
        return jsonFieldName;
    }

    public String format() {
        return format;
    }

    public boolean ignored() {
        return ignored;
    }

    public Class<?> type() {
        return type;
    }

    /**
     * 序列化（bean -> JSON）时输出的 key：
     * JSONField.name() 非空时取之，否则取 Java 字段名。
     */
    public String jsonName() {
        return jsonFieldName != null && !jsonFieldName.isEmpty() ? jsonFieldName : fieldName;
    }

    /**
     * 反序列化（JSON -> bean）时使用的 format 语义：
     * 仅当字段上 JSONField.name() 等于 Java 字段名时，format 才生效（与旧逻辑一致）。
     */
    public String toBeanFormat() {
        return fieldName.equals(jsonFieldName) ? format : null;
    }
}

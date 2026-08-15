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
 * @author Martin
 * @since 2026/8/15
 */
public interface JBeanAccessor {

    /**
     * 创建一个新的 bean 实例（对应默认无参构造）。
     */
    Object create();

    /**
     * 参与序列化的字段元信息数组
     */
    JBeanFieldMeta[] fields();

    /**
     * 读取第 index 个字段的值。
     */
    Object get(Object bean, int index);

    /**
     * 写入第 index 个字段的值。
     */
    void set(Object bean, int index, Object value);
}

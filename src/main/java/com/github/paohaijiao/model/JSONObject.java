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
package com.github.paohaijiao.model;

import com.github.paohaijiao.mapper.JBeanMapper;
import com.paohaijiao.javelin.util.JReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

public class JSONObject implements Map<String, Object>, JBeanMapper {
    private final Map<String, Object> map;

    public JSONObject() {
        this.map = new LinkedHashMap<>();
    }

    public JSONObject(int initialCapacity) {
        this.map = new LinkedHashMap<>(initialCapacity);
    }

    public JSONObject(Map<String, Object> map) {
        this.map = new LinkedHashMap<>(map);
    }

    public String getString(String key) {
        Object value = map.get(key);
        return value == null ? null : value.toString();
    }

    public Integer getInteger(String key) {
        Object value = map.get(key);
        return value == null ? null : Integer.valueOf(value.toString());
    }

    public Long getLong(String key) {
        Object value = map.get(key);
        return value == null ? null : Long.valueOf(value.toString());
    }

    public Double getDouble(String key) {
        Object value = map.get(key);
        return value == null ? null : Double.valueOf(value.toString());
    }

    public Boolean getBoolean(String key) {
        Object value = map.get(key);
        return value == null ? null : Boolean.valueOf(value.toString());
    }

    public JSONObject getJSONObject(String key) {
        Object value = map.get(key);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }
        if (value instanceof Map) {
            return new JSONObject((Map<String, Object>) value);
        }
        return null;
    }

    public JSONArray getJSONArray(String key) {
        Object value = map.get(key);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            JSONArray jsonArray = new JSONArray();
            for (Object item : list) {
                if (item instanceof Map) {
                    jsonArray.add(new JSONObject((Map<String, Object>) item));
                } else {
                    JSONObject wrapper = new JSONObject();
                    wrapper.put("value", item);
                    jsonArray.add(wrapper);
                }
            }
            return jsonArray;
        }
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            sb.append("\"").append(escape(entry.getKey())).append("\":");
            Object value = entry.getValue();
            if (value == null) {
                sb.append("null");
            } else if (value instanceof String) {
                sb.append("\"").append(escape((String) value)).append("\"");
            } else if (value instanceof Number || value instanceof Boolean) {
                sb.append(value);
            } else {
                sb.append(value.toString());
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // 简单的字符串转义
    public static String escape(String s) {
        if (s == null) return null;
        return s.replace("\"", "\\\"")
                .replace("\\", "\\\\")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public static JSONObject parseObject(String json) {
        JSONObject obj = new JSONObject();
        String content = json.trim().substring(1, json.length() - 1);
        String[] entries = content.split(",");
        for (String entry : entries) {
            String[] kv = entry.split(":", 2);
            String key = kv[0].trim().replaceAll("^\"|\"$", "");
            String value = kv[1].trim();
            if (value.startsWith("\"") && value.endsWith("\"")) {
                obj.put(key, value.substring(1, value.length() - 1));
            } else if (value.equals("true") || value.equals("false")) {
                obj.put(key, Boolean.valueOf(value));
            } else if (value.equals("null")) {
                obj.put(key, null);
            } else {
                try {
                    obj.put(key, Long.valueOf(value));
                } catch (NumberFormatException e) {
                    try {
                        obj.put(key, Double.valueOf(value));
                    } catch (NumberFormatException e2) {
                        obj.put(key, value);
                    }
                }
            }
        }
        return obj;
    }

    public static String toJSONString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof JSONObject) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new JSONObject((Map<String, Object>) obj).toString();
        }
        if (obj instanceof String) {
            return "\"" + obj.toString() + "\"";
        }
        return obj.toString();
    }

    @Override
    public <T> T toBean(Class<T> t) {
        try {
            T instance = JReflectionUtils.newInstance(t);
            List<Field> fields = JReflectionUtils.getAllFields(t);
            for (Field field : fields) {
                String fieldName = field.getName();
                if (this.containsKey(fieldName)) {
                    Object value = this.get(fieldName);
                    Class<?> fieldType = field.getType();
                    if (value instanceof Map && !Map.class.isAssignableFrom(fieldType)) {
                        JSONObject nestedJson = new JSONObject((Map<String, Object>) value);
                        value = nestedJson.toBean(fieldType);
                    } else if (fieldType.isEnum() && value instanceof String) {
                        value = JReflectionUtils.getEnumByName((Class<? extends Enum>) fieldType, (String) value);
                    } else if (value != null && !fieldType.isAssignableFrom(value.getClass())) {
                        if (fieldType == Integer.class || fieldType == int.class) {
                            value = this.getInteger(fieldName);
                        } else if (fieldType == Long.class || fieldType == long.class) {
                            value = this.getLong(fieldName);
                        } else if (fieldType == Double.class || fieldType == double.class) {
                            value = this.getDouble(fieldName);
                        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                            value = this.getBoolean(fieldName);
                        } else if (fieldType == String.class) {
                            value = this.getString(fieldName);
                        }
                    }
                    JReflectionUtils.setFieldValue(instance, fieldName, value);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("转换JSON对象为Bean失败", e);
        }
    }

    @Override
    public Map toMap() {
        return deepCopyMap(this.map);
    }

    @Override
    public JSONObject fromBean(Object bean) {
        if (bean == null) {
            return new JSONObject();
        }
        JSONObject json = new JSONObject();
        List<Field> fields = JReflectionUtils.getAllFields(bean.getClass());
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(bean);
                if (value == null) {
                    json.put(field.getName(), null);
                } else if (value instanceof Number || value instanceof Boolean || value instanceof String) {
                    json.put(field.getName(), value);
                } else if (!value.getClass().isArray() && !(value instanceof Collection) && !(value instanceof Map)) {
                    json.put(field.getName(), fromBean(value));
                } else if (value instanceof Collection) {
                    List<Object> list = new ArrayList<>();
                    for (Object item : (Collection<?>) value) {
                        list.add(item instanceof String || item instanceof Number || item instanceof Boolean ? item : fromBean(item));
                    }
                    json.put(field.getName(), list);
                } else {
                    json.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to convert bean to JSON", e);
            }
        }

        return json;
    }

    @Override
    public JSONObject fromMap(Map<String, Object> map) {
        return new JSONObject(map);
    }

    private Map<String, Object> deepCopyMap(Map<String, Object> original) {
        return deepCopyMap(original, new IdentityHashMap<>());
    }

    private Map<String, Object> deepCopyMap(Map<String, Object> original, IdentityHashMap<Object, Object> processed) {
        if (processed.containsKey(original)) {
            return null;
        }
        processed.put(original, null);
        Map<String, Object> copy = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : original.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                copy.put(entry.getKey(), deepCopyMap((Map<String, Object>) value, processed));
            } else if (value instanceof List) {
                copy.put(entry.getKey(), deepCopyList((List<?>) value, processed));
            } else {
                copy.put(entry.getKey(), value);
            }
        }
        return copy;
    }

    private List<Object> deepCopyList(List<?> original, IdentityHashMap<Object, Object> processed) {
        if (processed.containsKey(original)) {
            return null;
        }
        processed.put(original, null);
        List<Object> copy = new ArrayList<>();
        for (Object item : original) {
            if (item instanceof Map) {
                copy.add(deepCopyMap((Map<String, Object>) item, processed));
            } else if (item instanceof List) {
                copy.add(deepCopyList((List<?>) item, processed));
            } else {
                copy.add(item);
            }
        }
        return copy;
    }

    /**
     * 检查JSON对象是否包含指定键
     *
     * @param key 要检查的键名
     * @return 如果包含该键则返回true，否则返回false
     */
    public boolean has(String key) {
        return map.containsKey(key);
    }

    /**
     * 检查JSON对象是否包含指定键且值不为null
     *
     * @param key 要检查的键名
     * @return 如果包含该键且值不为null则返回true，否则返回false
     */
    public boolean hasNotNull(String key) {
        return map.containsKey(key) && map.get(key) != null;
    }
}

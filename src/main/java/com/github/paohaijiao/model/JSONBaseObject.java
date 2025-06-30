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
import com.github.paohaijiao.mapper.JNativeFormatMapper;
import com.github.paohaijiao.mapper.JNativeMapper;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickJSONLexer;
import com.github.paohaijiao.parser.JQuickJSONParser;
import com.github.paohaijiao.visitor.JSONCommonVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public abstract class JSONBaseObject implements Map<String, Object>, JNativeFormatMapper, JNativeMapper, JBeanMapper {
    protected Map<String, Object> map;

    protected JContext context;

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

    public Object getNativeValue(String fieldName, Class<?> fieldType, Object value) {
        if (fieldType == Integer.class || fieldType == int.class) {
            value = this.getInteger(fieldName);
        } else if (fieldType == Long.class || fieldType == long.class) {
            value = this.getLong(fieldName);
        } else if (fieldType == Double.class || fieldType == double.class) {
            value = this.getDouble(fieldName);
        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
            value = this.getBoolean(fieldName);
        } else if (fieldType == Date.class) {
            value = this.getDate(fieldName);
        } else if (fieldType == String.class) {
            value = this.getString(fieldName);
        }
        return value;
    }

    public Object getNativeForMatValue(String fieldName, Class<?> fieldType, Object value, String format) {
        if (fieldType == Integer.class || fieldType == int.class) {
            value = this.getInteger(format, fieldName);
        } else if (fieldType == Long.class || fieldType == long.class) {
            value = this.getLong(format, fieldName);
        } else if (fieldType == Double.class || fieldType == double.class) {
            value = this.getDouble(format, fieldName);
        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
            value = this.getBoolean(format, fieldName);
        } else if (fieldType == Date.class) {
            value = this.getDate(format, fieldName);
        } else if (fieldType == String.class) {
            value = this.getString(format, fieldName);
        }
        return value;
    }

    public static List<?> convertToList(Object value) {
        if (value == null) {
            return Collections.emptyList();
        }
        if (value instanceof List) {
            return (List<?>) value;
        }
        if (value.getClass().isArray()) {
            return Arrays.asList((Object[]) value);
        }
        throw new IllegalArgumentException("Object is neither List nor Array");
    }

    public Object getValue(Object obj) {
        if (null == obj) {
            return obj;
        }
        String string = (String) obj;
        if (StringUtils.isEmpty(string)) {
            return string;
        }
        if (string.startsWith("${") && string.endsWith("}")) {
            JQuickJSONLexer lexer = new JQuickJSONLexer(CharStreams.fromString(string));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JQuickJSONParser parser = new JQuickJSONParser(tokens);
            ParseTree tree = parser.json();
            JSONCommonVisitor visitor = new JSONCommonVisitor(this.context);
            Object value = visitor.visit(tree);
            return value;
        } else {
            return string;
        }

    }

}

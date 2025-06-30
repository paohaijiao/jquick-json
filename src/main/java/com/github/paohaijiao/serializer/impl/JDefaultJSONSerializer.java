package com.github.paohaijiao.serializer.impl; /// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
// */
//package com.github.paohaijiao.serializer.impl;
//
//import com.github.paohaijiao.model.JSONArray;
//import com.github.paohaijiao.model.JSONObject;
//import com.github.paohaijiao.serializer.JSONSerializer;
//import com.github.paohaijiao.support.JSONSupport;
//import com.paohaijiao.javelin.param.JContext;
//import com.paohaijiao.javelin.util.JStringUtils;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * packageName com.paohaijiao.javelin.serializer
// *
// * @author Martin
// * @version 1.0.0
// * @className DefaultJSONSerializer
// * @date 2025/6/20
// * @description
// */
//public class JDefaultJSONSerializer implements JSONSerializer {
//
//    private JContext context;
//
//    public JDefaultJSONSerializer() {
//        context = new JContext();
//    }
//
//    public JDefaultJSONSerializer(JContext context) {
//        context = context;
//    }
//
//    @Override
//    public String serialize(Object object) {
//        if (object == null) return "null";
//        if (object instanceof JSONObject || object instanceof JSONArray) {
//            return object.toString();
//        }
//        if (object instanceof String) {
//            return JStringUtils.trim((String) object);
//        }
//        if (object instanceof Number || object instanceof Boolean) {
//            return object.toString();
//        }
//        if (object.getClass().isArray()) {
//            return serializeArray(object);
//        }
//        if (object instanceof Collection) {
//            return serializeCollection((Collection<?>) object);
//        }
//        return new JSONObject().fromBean(object).toString();
//    }
//
//    @Override
//    public String serialize(JContext context, Object object) {
//        return "";
//    }
//
//    @Override
//    public <T> T deserialize(String json, Class<T> clazz) {
//        if (json == null || json.trim().isEmpty()) return null;
//        json = json.trim();
//        if (json.startsWith("{")) {
//            return JSONObject.parseObject(json).toBean(clazz);
//        }
//        if (json.startsWith("[")) {
//            return deserializeArrayOrCollection(json, clazz);
//        }
//        return deserializePrimitive(json, clazz);
//    }
//
//    @Override
//    public <T> T deserialize(JContext context, String json, Class<T> clazz) {
//        return null;
//    }
//
//
//    private String serializeArray(Object array) {
//        JSONArray jsonArray = new JSONArray();
//        int length = Array.getLength(array);
//        for (int i = 0; i < length; i++) {
//            jsonArray.add(new JSONObject().fromBean(Array.get(array, i)));
//        }
//        return jsonArray.toString();
//    }
//
//    private String serializeCollection(Collection<?> collection) {
//        JSONArray jsonArray = new JSONArray();
//        collection.forEach(item ->
//                jsonArray.add(new JSONObject().fromBean(item))
//        );
//        return jsonArray.toString();
//    }
//
//    @SuppressWarnings("unchecked")
//    private <T> T deserializeArrayOrCollection(String json, Class<T> clazz) {
//        JSONArray jsonArray = parseArray(json);
//        if (clazz.isArray()) {
//            return (T) toArray(jsonArray, clazz.getComponentType());
//        }
//        if (Collection.class.isAssignableFrom(clazz)) {
//            return (T) toCollection(jsonArray, clazz);
//        }
//        throw new IllegalArgumentException("Cannot deserialize array to " + clazz.getName());
//    }
//
//    private Object[] toArray(JSONArray jsonArray, Class<?> componentType) {
//        Object array = Array.newInstance(componentType, jsonArray.size());
//        for (int i = 0; i < jsonArray.size(); i++) {
//            Array.set(array, i, convertValue(jsonArray.get(i), componentType));
//        }
//        return (Object[]) array;
//    }
//
//    @SuppressWarnings("unchecked")
//    private Collection<?> toCollection(JSONArray jsonArray, Class<?> collectionType) {
//        try {
//            Collection<Object> collection = (Collection<Object>) collectionType.newInstance();
//            jsonArray.forEach(item ->
//                    collection.add(convertValue(item, Object.class))
//            );
//            return collection;
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to create collection", e);
//        }
//    }
//
//    private <T> T deserializePrimitive(String json, Class<T> clazz) {
//        if (clazz == String.class) {
//            return clazz.cast(unquoteString(json));
//        }
//        try {
//            if (clazz == Integer.class || clazz == int.class) return clazz.cast(Integer.valueOf(json));
//            if (clazz == Long.class || clazz == long.class) return clazz.cast(Long.valueOf(json));
//            if (clazz == Double.class || clazz == double.class) return clazz.cast(Double.valueOf(json));
//            if (clazz == Boolean.class || clazz == boolean.class) return clazz.cast(Boolean.valueOf(json));
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Invalid value for type " + clazz.getSimpleName());
//        }
//        throw new IllegalArgumentException("Unsupported type: " + clazz.getName());
//    }
//
//    private String unquoteString(String str) {
//        return str.startsWith("\"") ? str.substring(1, str.length() - 1) : str;
//    }
//
//    private Object convertValue(Object value, Class<?> targetType) {
//        if (value == null) return null;
//        if (targetType.isInstance(value)) return value;
//        if (value instanceof JSONObject) {
//            return ((JSONObject) value).toBean(targetType);
//        }
//        return deserializePrimitive(value.toString(), targetType);
//    }
//
//    public static JSONArray parseArray(String json) {
//        if (json == null) {
//            throw new IllegalArgumentException("JSON string cannot be null");
//        }
//        String trimmed = json.trim();
//        if (!trimmed.startsWith("[") || !trimmed.endsWith("]")) {
//            throw new IllegalArgumentException("Not a valid JSON array");
//        }
//        String content = trimmed.substring(1, trimmed.length() - 1).trim();
//        if (content.isEmpty()) {
//            return new JSONArray();
//        }
//        List<Object> list = new ArrayList<>();
//        int index = 0;
//        int length = content.length();
//        while (index < length) {
//            // 跳过空白字符
//            while (index < length && Character.isWhitespace(content.charAt(index))) {
//                index++;
//            }
//            if (index >= length) break;
//            char c = content.charAt(index);
//            Object value;
//            if (c == '"') {
//                value = JSONSupport.parseString(content, index);
//                index = ((int[]) value)[1];
//                value = ((Object[]) value)[0];
//            }
//            else if (c == '{') {
//                value =JSONSupport. parseObject(content, index);
//                index = ((int[]) value)[1];
//                value = ((Object[]) value)[0];
//            }
//            else if (c == '[') {
//                value = parseArray(content.substring(index));
//                index += value.toString().length();
//            }
//            else if (c == 't' && content.startsWith("true", index)) { // true
//                value = true;
//                index += 4;
//            }
//            else if (c == 'f' && content.startsWith("false", index)) { // false
//                value = false;
//                index += 5;
//            }
//            else if (c == 'n' && content.startsWith("null", index)) { // null
//                value = null;
//                index += 4;
//            }
//            else { // 数字
//                value = JSONSupport.parseNumber(content, index);
//                index = ((int[]) value)[1];
//                value = ((Object[]) value)[0];
//            }
//            list.add(value);
//            while (index < length && Character.isWhitespace(content.charAt(index))) {
//                index++;
//            }
//            if (index < length && content.charAt(index) == ',') {
//                index++;
//            }
//        }
//        return new JSONArray(list);
//    }
//}

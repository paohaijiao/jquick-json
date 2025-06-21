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
package com.github.paohaijiao.serializer;

import com.paohaijiao.javelin.adaptor.JQuickAdaptor;
import com.github.paohaijiao.executor.JSONExecutor;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.model.JsonResponse;
import com.paohaijiao.javelin.param.JContext;
import com.paohaijiao.javelin.resource.JQuickReader;
import com.paohaijiao.javelin.resource.impl.JQuickReSourceFileReader;
import com.github.paohaijiao.support.JSONSupport;
import com.paohaijiao.javelin.util.JStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * packageName com.paohaijiao.javelin.serializer
 *
 * @author Martin
 * @version 1.0.0
 * @className DefaultJSONSerializer
 * @date 2025/6/20
 * @description
 */
public class JQuickJSONSerializer implements JSONSerializer {
    private JContext context;

    public JQuickJSONSerializer() {
        this.context = new JContext();
    }

    public JQuickJSONSerializer(JContext context) {
        this.context = context;
    }

    @Override
    public String serialize(Object object) {
        if (object == null) return "null";
        if (object instanceof JSONObject || object instanceof JSONArray) {
            return object.toString();
        }
        if (object instanceof String) {
            return StringUtils.trim((String) object);
        }
        if (object instanceof Number || object instanceof Boolean) {
            return object.toString();
        }
        if (object.getClass().isArray()) {
            return serializeArray(object);
        }
        if (object instanceof Collection) {
            return serializeCollection((Collection<?>) object);
        }
        return new JSONObject().fromBean(object).toString();
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) {
        if (json == null || json.trim().isEmpty()) return null;
        json = json.trim();
        if (json.startsWith("{")) {
            JSONExecutor executor = new JSONExecutor(context);
            executor.addErrorListener(error -> {});
            JQuickReader fileReader = new JQuickReSourceFileReader("rule.txt");
            JQuickAdaptor context = new JQuickAdaptor(fileReader);
            System.out.println(context.getRuleContent());
            JsonResponse jsonObject = executor.execute(context.getRuleContent());
            JsonResponse response= jsonObject.getData();
        }
        if (json.startsWith("[")) {
            JSONExecutor executor = new JSONExecutor(context);
            executor.addErrorListener(error -> {});
            JQuickReader fileReader = new JQuickReSourceFileReader("rule.txt");
            JQuickAdaptor context = new JQuickAdaptor(fileReader);
            System.out.println(context.getRuleContent());
            JsonResponse jsonObject = executor.execute(context.getRuleContent());
            JsonResponse response= jsonObject.getData();
        }
        return deserializePrimitive(json, clazz);
    }


    private String serializeArray(Object array) {
        JSONArray jsonArray = new JSONArray();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            jsonArray.add(new JSONObject().fromBean(Array.get(array, i)));
        }
        return jsonArray.toString();
    }

    private String serializeCollection(Collection<?> collection) {
        JSONArray jsonArray = new JSONArray();
        collection.forEach(item ->
                jsonArray.add(new JSONObject().fromBean(item))
        );
        return jsonArray.toString();
    }

    @SuppressWarnings("unchecked")
    private <T> T deserializeArrayOrCollection(String json, Class<T> clazz) {
        JSONArray jsonArray = parseJSONArray(json);
        if (clazz.isArray()) {
            return (T) toArray(jsonArray, clazz.getComponentType());
        }
        if (Collection.class.isAssignableFrom(clazz)) {
            return (T) toCollection(jsonArray, clazz);
        }
        throw new IllegalArgumentException("Cannot deserialize array to " + clazz.getName());
    }

    private Object[] toArray(JSONArray jsonArray, Class<?> componentType) {
        Object array = Array.newInstance(componentType, jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            Array.set(array, i, convertValue(jsonArray.get(i), componentType));
        }
        return (Object[]) array;
    }

    @SuppressWarnings("unchecked")
    private Collection<?> toCollection(JSONArray jsonArray, Class<?> collectionType) {
        try {
            Collection<Object> collection = (Collection<Object>) collectionType.newInstance();
            jsonArray.forEach(item ->
                    collection.add(convertValue(item, Object.class))
            );
            return collection;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create collection", e);
        }
    }

    private <T> T deserializePrimitive(String json, Class<T> clazz) {
        if (clazz == String.class) {
            return clazz.cast(JStringUtils.trim(json));
        }
        try {
            if (clazz == Integer.class || clazz == int.class) return clazz.cast(Integer.valueOf(json));
            if (clazz == Long.class || clazz == long.class) return clazz.cast(Long.valueOf(json));
            if (clazz == Double.class || clazz == double.class) return clazz.cast(Double.valueOf(json));
            if (clazz == Boolean.class || clazz == boolean.class) return clazz.cast(Boolean.valueOf(json));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for type " + clazz.getSimpleName());
        }
        throw new IllegalArgumentException("Unsupported type: " + clazz.getName());
    }

    private Object convertValue(Object value, Class<?> targetType) {
        if (value == null) return null;
        if (targetType.isInstance(value)) return value;
        if (value instanceof JSONObject) {
            return ((JSONObject) value).toBean(targetType);
        }
        return deserializePrimitive(value.toString(), targetType);
    }

    private JSONArray parseJSONArray(String json) {
        return JSONSupport.parseArray(json);
    }
}

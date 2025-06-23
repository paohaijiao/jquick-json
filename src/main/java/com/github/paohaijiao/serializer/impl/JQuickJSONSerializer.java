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
package com.github.paohaijiao.serializer.impl;

import com.github.paohaijiao.executor.JSONExecutor;
import com.github.paohaijiao.model.JSONArray;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.parser.JQuickJSONLexer;
import com.github.paohaijiao.parser.JQuickJSONParser;
import com.github.paohaijiao.serializer.JSONSerializer;
import com.github.paohaijiao.visitor.JSONCommonVisitor;
import com.paohaijiao.javelin.console.JConsole;
import com.paohaijiao.javelin.enums.JLogLevel;
import com.paohaijiao.javelin.exception.JAntlrExecutionException;
import com.paohaijiao.javelin.param.JContext;
import com.paohaijiao.javelin.util.JObjectConverter;
import com.paohaijiao.javelin.util.JStringUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
        return new JSONObject(context).fromBean(object).toString();
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) {
        JSONExecutor executor = new JSONExecutor();
        executor.addErrorListener(error -> {
            System.err.printf("错误: 行%d:%d - %s%n", error.getLine(), error.getCharPosition(), error.getMessage());
            System.err.println("规则栈: " + error.getRuleStack());
        });
        try {
            Object result = executor.execute(json);
            JConsole console = new JConsole();
            console.log(JLogLevel.INFO,"parse result:"+result);
            return clazz.cast(result);
        } catch (JAntlrExecutionException e) {
            System.err.println("解析失败: " + e.getMessage());
            e.getErrors().forEach(err ->
                    System.err.println(" - " + err.getMessage()));
        }
        return null;
    }

    @Override
    public <T> T deserialize(JContext context, String json, Class<T> clazz) {
        return null;
    }


    private String serializeArray(Object array) {
        JSONArray jsonArray = new JSONArray();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            jsonArray.add(new JSONObject(this.context).fromBean(Array.get(array, i)));
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
        JSONArray jsonArray = JSONArray.parseJSONArray(json);
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
            if (clazz == Date.class || clazz == Date.class) return clazz.cast(getDate(json));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for type " + clazz.getSimpleName());
        }
        throw new IllegalArgumentException("Unsupported type: " + clazz.getName());
    }
    private Date getDate(String date){
        String[] possibleDateFormats = {
                "yyyy-MM-dd", "dd/MM/yyyy", "MM-dd-yyyy",
                "yyyy MM dd", "yyyy.MM.dd", "MMM dd, yyyy"
        };
        Date data = null;
        try {
            data = DateUtils.parseDate(date, possibleDateFormats);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    
    private Object convertValue(Object value, Class<?> targetType) {
        if (value == null) return null;
        if (targetType.isInstance(value)) return value;
        if (value instanceof JSONObject) {
            return ((JSONObject) value).toBean(targetType);
        }
        return deserializePrimitive(value.toString(), targetType);
    }


}

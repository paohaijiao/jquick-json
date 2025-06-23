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

import com.github.paohaijiao.executor.JSONExecutor;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.enums.JLogLevel;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.util.JObjectConverter;

import java.util.*;

public class JSONArray implements List<Object> {

    private final List<Object> list;

    public JSONArray() {
        this.list = new ArrayList<>();
    }

    public JSONArray(int initialCapacity) {
        this.list = new ArrayList<>(initialCapacity);
    }

    public JSONArray(List<?> list) {
        this.list = new ArrayList<>(list);
    }

    public String getString(int index) {
        Object value = list.get(index);
        return value == null ? null : value.toString();
    }

    public Integer getInteger(int index) {
        Object value = list.get(index);
        return value == null ? null : Integer.valueOf(value.toString());
    }

    public Long getLong(int index) {
        Object value = list.get(index);
        return value == null ? null : Long.valueOf(value.toString());
    }

    public Double getDouble(int index) {
        Object value = list.get(index);
        return value == null ? null : Double.valueOf(value.toString());
    }

    public Boolean getBoolean(int index) {
        Object value = list.get(index);
        return value == null ? null : Boolean.valueOf(value.toString());
    }

    public JSONObject getJSONObject(int index) {
        Object value = list.get(index);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }
        if (value instanceof Map) {
            return new JSONObject((Map<String, Object>) value);
        }
        return null;
    }

    public JSONArray getJSONArray(int index) {
        Object value = list.get(index);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        if (value instanceof List) {
            return new JSONArray((List<Object>) value);
        }
        return null;
    }

    // 实现List接口的方法
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Object o) {
        return list.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Object> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Object> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Object get(int index) {
        return list.get(index);
    }

    @Override
    public Object set(int index, Object element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, Object element) {
        list.add(index, element);
    }

    @Override
    public Object remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (Object value : list) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            if (value == null) {
                sb.append("null");
            } else if (value instanceof String) {
                sb.append("\"").append(JSONObject.escape((String) value)).append("\"");
            } else if (value instanceof Number || value instanceof Boolean) {
                sb.append(value);
            } else {
                sb.append(value.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public static JSONArray parseJSONArray(String json) {
        JSONExecutor executor = new JSONExecutor();
        executor.addErrorListener(error -> {
            System.err.printf("错误: 行%d:%d - %s%n", error.getLine(), error.getCharPosition(), error.getMessage());
            System.err.println("规则栈: " + error.getRuleStack());
        });
        try {
            Object result = executor.execute(json);
            JConsole console = new JConsole();
            console.log(JLogLevel.INFO,"parse result:"+result);
            List<?> list= JObjectConverter.assign(result, List.class);
            return new JSONArray(list);
        } catch (JAntlrExecutionException e) {
            System.err.println("解析失败: " + e.getMessage());
            e.getErrors().forEach(err ->
                    System.err.println(" - " + err.getMessage()));
        }
        return new JSONArray();
    }
    public List<?>toCollection(){
        return list;
    }

}

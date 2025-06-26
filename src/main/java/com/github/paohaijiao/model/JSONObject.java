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

import com.github.paohaijiao.anno.JSONField;
import com.github.paohaijiao.anno.JSONIgnore;
import com.github.paohaijiao.mapper.JBeanMapper;
import com.github.paohaijiao.mapper.JNativeFormatMapper;
import com.github.paohaijiao.mapper.JNativeMapper;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.merge.JMergeStrategy;
import com.github.paohaijiao.merge.impl.JDefaultJsonMerger;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.support.JSonMerge;
import com.github.paohaijiao.util.JReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class JSONObject extends JSONBaseObject implements Map<String, Object>, JNativeFormatMapper, JNativeMapper,JBeanMapper {


    public JSONObject() {
        this.map = new LinkedHashMap<>();
        this.context = new JContext();
    }
    public JSONObject(JContext context) {
        this.map = new LinkedHashMap<>();
        this.context = context;
    }

    public JSONObject(int initialCapacity) {
        this.map = new LinkedHashMap<>(initialCapacity);
        this.context = new JContext();
    }
    public JSONObject(int initialCapacity,JContext context) {
        this.map = new LinkedHashMap<>(initialCapacity);
        this.context = context;
    }

    public JSONObject(Map<String, Object> map) {
        this.map = new LinkedHashMap<>(map);
        this.context = new JContext();
    }
    public JSONObject(Map<String, Object> map,JContext context) {
        this.map = new LinkedHashMap<>(map);
        this.context =context;
    }
    @Override
    public String getString(String key) {
        Object value = map.get(key);
        return value == null ? null : value.toString();
    }
    @Override
    public String getString(String format, Object value) {
        String formatString = String.format(format, value);
        return formatString;
    }
    @Override
    public Integer getInteger(String key) {
        Object value = map.get(key);
        return value == null ? null : Integer.valueOf(value.toString());
    }
    @Override
    public String getInteger(String format, Object value) {
            DecimalFormat df = new DecimalFormat(format);
            Integer number=Integer.valueOf(value.toString());
            String formatted = df.format(number);
            return formatted;
    }
    @Override
    public Long getLong(String key) {
        Object value = map.get(key);
        return value == null ? null : Long.valueOf(value.toString());
    }
    @Override
    public String getLong(String format, Object value) {
            DecimalFormat df = new DecimalFormat(format);
            Integer number=Integer.valueOf(value.toString());
            String formatted = df.format(number);
            return formatted;
    }
    @Override
    public Double getDouble(String key) {
        Object value = map.get(key);
        return value == null ? null : Double.valueOf(value.toString());
    }
    @Override
    public String getDouble(String format,Object value) {

            DecimalFormat df = new DecimalFormat(format);
            Integer number=Integer.valueOf(value.toString());
            String formatted = df.format(number);
            return formatted;
    }
    @Override
    public Boolean getBoolean(String key) {
        Object value = map.get(key);
        return value == null ? null : Boolean.valueOf(value.toString());
    }
    @Override
    public String getBoolean(String format, Object value) {
        return Boolean.valueOf(value.toString())+"";
    }
    @Override
    public Date getDate(String key) {
        Object value = map.get(key);
        if(null==value){
            return null;
        }else if(value instanceof Date){
            return (Date)value;
        }
        JAssert.throwNewException("invalid date type");
        return null;
    }


    @Override
    public String getDate(String format,  Object value) {
        Date d= (Date)value;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }
    @Override
    public BigDecimal getBigDecimal(String key) {
        Object value = map.get(key);
        if(null==value){
            return null;
        }else if(value instanceof BigDecimal){
            return (BigDecimal)value;
        }
        JAssert.throwNewException("invalid BigDecimal type");
        return null;
    }



    @Override
    public String getBigDecimal(String format, Object value) {
        if(value instanceof BigDecimal){
            BigDecimal d= (BigDecimal)value;
            DecimalFormat df= new DecimalFormat(format);
            return df.format(d);
        }
        return null;
    }
    @Override
    public Float getFloat(String key) {
        Object value = map.get(key);
        if(null==value){
            return null;
        }else if(value instanceof Float){
            return (Float)value;
        }
        return null;
    }
    @Override
    public String getFloat(String format, Object value) {
        if(value instanceof Float){
            Float d= (Float)value;
            String formatted = String.format(format, d);
            return formatted;
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
        for (Entry<String, Object> entry : map.entrySet()) {
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



    @Override
    public <T> T toBean(Class<T> t) {
        try {
            T instance = JReflectionUtils.newInstance(t);
            List<Field> fields = JReflectionUtils.getAllFields(t);
            for (Field field : fields) {
                JSONIgnore ignore=field.getAnnotation(JSONIgnore.class);
                if(ignore != null) {
                    continue;
                }
                String fieldName = field.getName();
                if (this.containsKey(fieldName)) {
                    JSONField jsonField=field.getAnnotation(JSONField.class);
                    String format =null;
                    if(jsonField != null) {
                        if(fieldName.equals(jsonField.name())){
                            format=jsonField.format();
                        }
                    }
                    Object value = this.get(fieldName);
                    Class<?> fieldType = field.getType();
                    if (value instanceof Map && !Map.class.isAssignableFrom(fieldType)) {
                        JSONObject nestedJson = new JSONObject((Map<String, Object>) value);
                        value = nestedJson.toBean(fieldType);
                    } else if (fieldType.isEnum() && value instanceof String) {
                        value = JReflectionUtils.getEnumByName((Class<? extends Enum>) fieldType, (String) value);
                    } else if (value != null && !fieldType.isAssignableFrom(value.getClass())) {
                       if(format==null){
                           value = getNativeValue(fieldName,fieldType,value);
                       }else{
                           value = getNativeForMatValue(fieldName,fieldType,value,format);
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
                JSONIgnore ignore=field.getAnnotation(JSONIgnore.class);
                if(ignore != null) {
                    continue;
                }
                String fieldName = field.getName();
                JSONField jsonField=field.getAnnotation(JSONField.class);
                String format =null;
                if(jsonField != null) {
                    if(!StringUtils.isEmpty(jsonField.name())){
                        fieldName=jsonField.name();
                    }
                    if(!StringUtils.isEmpty(jsonField.format())){
                        format=jsonField.format();
                    }
                }
                if (value == null) {
                    json.put(fieldName, null);
                } if (value instanceof Map) {
                    JSONObject object=new JSONObject((Map)value);
                    json.put(fieldName, object);
                }
                else if (value.getClass().isArray() || (value instanceof Collection) ) {
                    List<?> list=convertToList(value);
                    JSONArray array=new JSONArray(list);
                    json.put(fieldName, array);
                } else if (value instanceof Collection) {
                    List<Object> list = new ArrayList<>();
                    for (Object item : (Collection<?>) value) {
                        list.add( fromBean(item));
                    }
                    json.put(fieldName, list);
                } else if (value instanceof BigDecimal){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getBigDecimal(format,value));
                    }
                } else if (value instanceof Float){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getFloat(format,value));
                    }
                }else if (value instanceof Double){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getDouble(format,value));
                    }
                }else if (value instanceof Date){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getDate(format,value));
                    }
                }else if (value instanceof Integer){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getInteger(format,value));
                    }
                }else if (value instanceof Long){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getLong(format,value));
                    }
                }else if (value instanceof Boolean){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getBoolean(format,value));
                    }
                }
                else if (value instanceof Date){
                    if(format==null){
                        json.put(fieldName, value);
                    }else{
                        json.put(fieldName,getDate(format,value));
                    }
                } else if (value instanceof String){
                    if(format==null){
                        json.put(fieldName, getValue(value));
                    }else{
                        json.put(fieldName,getString(format,value));
                    }
                }
                else {//
                    String fieldNameStr=field.getName();
                    JSONObject obj= fromBean(value);
                    json.put(fieldName,obj);
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
        for (Entry<String, Object> entry : original.entrySet()) {
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
    public JSONObject mergeWith(JSONObject other, JMergeStrategy strategy) {
        return new JDefaultJsonMerger().strategy(strategy).merge(this, other);
    }

    public JSONObject deepMergeWith(JSONObject other) {
        return JSonMerge.deepMerge(this, other);
    }

    public JSONObject shallowMergeWith(JSONObject other) {
        return JSonMerge.shallowMerge(this, other);
    }


}

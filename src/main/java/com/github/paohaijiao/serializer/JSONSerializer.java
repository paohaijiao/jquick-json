package com.github.paohaijiao.serializer;

import com.paohaijiao.javelin.param.JContext;

public interface JSONSerializer {

    String serialize(Object object);

    <T> T deserialize(String json, Class<T> clazz);
    <T> T deserialize(JContext context,String json, Class<T> clazz);
}

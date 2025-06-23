package com.github.paohaijiao.serializer;


public interface JSONSerializer {

    String serialize(Object object);

    <T> T deserialize(String json, Class<T> clazz);
}

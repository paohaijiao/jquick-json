package com.github.paohaijiao.mapper;

public interface JNativeFormatMapper {

    public String getString(String format,Object key) ;

    public String getInteger(String format,Object key) ;

    public String getLong(String format,Object key) ;

    public String getDouble(String format,Object key) ;

    public String getBoolean(String format,Object key);

    public String getDate(String format,Object key);

    public String getBigDecimal(String format,Object key);
    public String getFloat(String format,Object key);

}

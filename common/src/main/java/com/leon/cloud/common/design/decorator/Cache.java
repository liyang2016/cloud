package com.leon.cloud.common.design.decorator;

public interface Cache {

    String getCache(String key);

    String removeCache(String key);

    void putCache(String key,String value);
}

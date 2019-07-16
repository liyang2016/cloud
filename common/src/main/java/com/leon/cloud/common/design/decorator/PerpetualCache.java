package com.leon.cloud.common.design.decorator;

import java.util.HashMap;
import java.util.Map;

public class PerpetualCache implements Cache{
    private Map<String,String> map=new HashMap();

    @Override
    public String getCache(String key) {
        return map.get(key);
    }

    @Override
    public String removeCache(String key) {
        return map.remove(key);
    }

    @Override
    public void putCache(String key, String value) {
        map.put(key,value);
    }
}

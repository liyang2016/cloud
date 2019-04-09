package com.leon.cloud.common.uilts;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by leon on 2019/3/21.
 */

public class CacheMap {
    private static final Logger logger = LoggerFactory.getLogger(CacheMap.class);
    private static Cache<String, Object> cache;

    static {
        cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(24, TimeUnit.HOURS).initialCapacity(10)
                .removalListener(notification -> {
                    if (logger.isInfoEnabled()) {
                        logger.info("{}->移除缓存{}:{}", notification.getCause(), notification.getKey(), notification.getValue());
                    }
                }).build();
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return StringUtils.isNoneEmpty(key) ? cache.getIfPresent(key) : null;
    }

    /**
     * 存入缓存
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        if (StringUtils.isNoneEmpty(key) && value != null) {
            cache.put(key, value);
        }
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public static void remove(String key) {
        if (StringUtils.isNoneEmpty(key)) {
            cache.invalidate(key);
        }
    }

    /**
     * 批量移除
     *
     * @param keys
     */
    public static void removeAll(List<String> keys) {
        if (keys != null && keys.size() > 0) {
            cache.invalidateAll(keys);
        }
    }
}

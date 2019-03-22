package com.leon.cloud.common.uilts;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leon on 2019/3/21.
 */
public class CacheUtils {

    public static void main(String[] args) {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder().maximumSize(2)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .removalListener(removalNotification -> System.out.println(removalNotification.getCause()))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) {
                        return key.toString();
                    }
                });
        System.out.println(cache.getIfPresent(1));
        try {
            System.out.println(cache.get(1));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(cache.getIfPresent(1));

        cache.put(3, "3");
        cache.put(4, "4");
        System.out.println(cache.asMap());


        AtomicInteger atomicInteger=new AtomicInteger();
        atomicInteger.get();
    }
}

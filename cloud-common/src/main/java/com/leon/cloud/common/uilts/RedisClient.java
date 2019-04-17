package com.leon.cloud.common.uilts;

import lombok.extern.log4j.Log4j2;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Log4j2
public class RedisClient {

    private static RedissonClient redisClient;

    public static void setRedisClient(RedissonClient redisClient) {
        RedisClient.redisClient = redisClient;
    }

    public static RedissonClient getRedisClient() {
        if (redisClient != null && !redisClient.isShutdown()) {
            log.info("RedissonClient exists");
            return redisClient;
        }else{
            //单机创建配置
            Config config = new Config();
            URL url = ClassPathResource.class.getClassLoader().getResource("redis.yml");
            try {
                config = Config.fromYAML(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RedissonClient client=Redisson.create(config);
            setRedisClient(client);
            return client;
        }
    }

    public static boolean tryGetLock(RedissonClient client,String lockKey,int waitTime,int expireTime){
        RLock rLock=client.getLock(lockKey);
        try {
            return rLock.tryLock(waitTime,expireTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean tryReleaseLock(RedissonClient client,String lockKey){
        RLock rLock=client.getLock(lockKey);
        boolean result=false;
        if(rLock.isHeldByThread(Thread.currentThread().getId())){
            result=rLock.forceUnlock();
        }
        return result;
    }

    public static void main(String[] args) {
        RedissonClient client= getRedisClient();
        RSet<String> rSet = client.getSet("setfail");
        rSet.forEach(log::info);

        String o = "de";

        //Bloom过滤器
        RBloomFilter<String> filter = client.getBloomFilter("filter");
        filter.tryInit(1000000, 0.01);
        boolean add = filter.add(o);
        if (add) {
            log.info("add success");
        }

        boolean con = filter.contains(o);
        if (con) {
            log.info("contains");
        }

        client.shutdown();
    }



}

package com.leon.cloud.common.design.decorator;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantCache implements Cache{
    private Cache delegate;
    private ReentrantReadWriteLock reentrantLock;

    public ReentrantCache(Cache cache){
        this.delegate=cache;
        reentrantLock=new ReentrantReadWriteLock();
    }

    @Override
    public String getCache(String key) {
        reentrantLock.readLock().lock();
        String result=delegate.getCache(key);
        reentrantLock.readLock().unlock();
        return result;
    }

    @Override
    public String removeCache(String key) {
        reentrantLock.writeLock().lock();
        String result=delegate.removeCache(key);
        reentrantLock.writeLock().unlock();
        return result;
    }

    @Override
    public void putCache(String key, String value) {
        reentrantLock.writeLock().lock();
        delegate.putCache(key,value);
        reentrantLock.writeLock().unlock();
    }


    public static void main(String[] args) {
        Cache cache=new PerpetualCache();
        Cache reentrantCache=new ReentrantCache(cache);

        reentrantCache.putCache("key","value");
        System.out.println(reentrantCache.getCache("key"));
        System.out.println(reentrantCache.removeCache("key"));
    }
}

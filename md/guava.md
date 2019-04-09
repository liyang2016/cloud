### cache
#### 创建方式
创建CacheLoader
创建Callable

```java
//CacheLoader创建cache
LoadingCache<Integer, String> cache = CacheBuilder.newBuilder().maximumSize(2)
        //设置缓存超时时间
        .expireAfterAccess(3, TimeUnit.SECONDS)
        //设置缓存被移除的监听器
        .removalListener(removalNotification -> System.out.println(removalNotification.getValue().toString()+removalNotification.getCause()))
        .build(new CacheLoader<Integer, String>() {
            @Override
            public String load(Integer key) {
                return key.toString();
            }
        });
//Callable创建缓存
Cache<Integer,String> integerStringCache=CacheBuilder.newBuilder().maximumSize(2).build();
Integer key= 1;
try {
    String value=integerStringCache.get(key, key::toString);
    System.out.println(value);
} catch (ExecutionException e) {
    e.printStackTrace();
}


//获取缓存，不存在返回NULL
System.out.println(cache.getIfPresent(1));
try {
    //获取缓存，缓存不存在，先添加缓存
    System.out.println(cache.get(1));
} catch (ExecutionException e) {
    e.printStackTrace();
}
```
#### 显示插入
cache.put()

#### 缓存回收
根据大小方式
根据权重方式，设置最大权重，重写计算插入缓存的权重的方法
定时回收   expireAfterAccess->update write read 操作时缓存被清除
           expireAfterWrite->update write 操作时缓存被清除
#### 刷新
LoadingCache.refresh(key)  可以异步进行，为缓存载入新值      
#### 显示清除
Cache.invalidate(key)
Cache.invalidateAll(keys)
Cache.invalidateAll()

#### 移除监听器
缓存被移除时，执行操作

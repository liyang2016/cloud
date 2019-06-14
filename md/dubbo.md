### dubbo注册中心数据
节点名解析：
dubbo://10.0.75.1:20880/org.apache.dubbo.demo.DemoService
anyhost=true
application=dubbo-demo-api-provider
default.deprecated=false
default.dynamic=false
default.export=true
default.register=true
deprecated=false
dubbo=2.0.2
dynamic=false
export=true
generic=false
interface=org.apache.dubbo.demo.DemoService
methods=sayHello
pid=8024
register=true
release=
side=provider
timestamp=1554859620977

### HashedWheelTimer
客户端重连 心跳检测
服务端 心跳检测
```java
public HeaderExchangeClient(Client client, boolean startTimer) {
        Assert.notNull(client, "Client can't be null");
        this.client = client;
        this.channel = new HeaderExchangeChannel(client);

        if (startTimer) {
            URL url = client.getUrl();
            startReconnectTask(url);
            startHeartBeatTask(url);
        }
    }
    
 /**
 * 获取通道最后读写时间，超时则关闭通道
*/
 public HeaderExchangeServer(Server server) {
         Assert.notNull(server, "server == null");
         this.server = server;
         startIdleCheckTask(getUrl());
     }
```


### SPI
org.apache.dubbo.common.extension.ExtensionLoader#createExtension中加载所有的SPI本地接口文件保存包含拷贝构造函数的Wapper类（Dubbo的AOP）
返回Wapper类的同时注入创建实例所需的属性对象Dubbo的IOC
cachedWrapperClasses在加载中读取所有的SPI接口文件，保存存在拷贝构造函数的类
```java
public T getExtension(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Extension name == null");
        }
        if ("true".equals(name)) {
            return getDefaultExtension();
        }
        Holder<Object> holder = getOrCreateHolder(name);
        Object instance = holder.get();
        //双重检测 保证instance线程安全
        if (instance == null) {
            synchronized (holder) {
                instance = holder.get();
                if (instance == null) {
                    instance = createExtension(name);
                    holder.set(instance);
                }
            }
        }
        return (T) instance;
    }
    
private T createExtension(String name) {
        Class<?> clazz = getExtensionClasses().get(name);
        if (clazz == null) {
            throw findException(name);
        }
        try {
            T instance = (T) EXTENSION_INSTANCES.get(clazz);
            if (instance == null) {
                EXTENSION_INSTANCES.putIfAbsent(clazz, clazz.newInstance());
                instance = (T) EXTENSION_INSTANCES.get(clazz);
            }
            injectExtension(instance);
            Set<Class<?>> wrapperClasses = cachedWrapperClasses;
            if (CollectionUtils.isNotEmpty(wrapperClasses)) {
                for (Class<?> wrapperClass : wrapperClasses) {
                    //存在Wapper类时，创建实例的过程
                    instance = injectExtension((T) wrapperClass.getConstructor(type).newInstance(instance));
                }
            }
            return instance;
        } catch (Throwable t) {
            throw new IllegalStateException("Extension instance (name: " + name + ", class: " +
                    type + ") couldn't be instantiated: " + t.getMessage(), t);
        }
    }
    
    
    private T injectExtension(T instance) {
            try {
                if (objectFactory != null) {
                    for (Method method : instance.getClass().getMethods()) {
                        //仅支持set方式注入
                        if (isSetter(method)) {
                            /**
                             * Check {@link DisableInject} to see if we need auto injection for this property
                             */
                            if (method.getAnnotation(DisableInject.class) != null) {
                                continue;
                            }
                            Class<?> pt = method.getParameterTypes()[0];
                            if (ReflectUtils.isPrimitives(pt)) {
                                continue;
                            }
                            try {
                                String property = getSetterProperty(method);
                                Object object = objectFactory.getExtension(pt, property);
                                if (object != null) {
                                    method.invoke(instance, object);
                                }
                            } catch (Exception e) {
                                logger.error("Failed to inject via method " + method.getName()
                                        + " of interface " + type.getName() + ": " + e.getMessage(), e);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return instance;
        }
```

SPI Adaptive自适应扩展点 通过URl对象中指定参数，有些拓展并不想在框架启动阶段被加载，而是希望在拓展方法被调用时，根据运行时参数进行加载。
通过生成代码的方式，有动态加载框架编译加载到JVM中执行
```java
package org.apache.dubbo.common.extension.ext1;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class SimpleExt$Adaptive implements org.apache.dubbo.common.extension.ext1.SimpleExt {
    public java.lang.String echo(org.apache.dubbo.common.URL arg0, java.lang.String arg1) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        org.apache.dubbo.common.URL url = arg0;
        String extName = url.getParameter("simple.ext", "impl1");
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (org.apache.dubbo.common.extension.ext1.SimpleExt) name from url (" + url.toString() + ") use keys([simple.ext])");
        org.apache.dubbo.common.extension.ext1.SimpleExt extension = (org.apache.dubbo.common.extension.ext1.SimpleExt) ExtensionLoader.getExtensionLoader(org.apache.dubbo.common.extension.ext1.SimpleExt.class).getExtension(extName);
        return extension.echo(arg0, arg1);
    }

    public java.lang.String yell(org.apache.dubbo.common.URL arg0, java.lang.String arg1) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        org.apache.dubbo.common.URL url = arg0;
        String extName = url.getParameter("key1", url.getParameter("key2", "impl1"));
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (org.apache.dubbo.common.extension.ext1.SimpleExt) name from url (" + url.toString() + ") use keys([key1, key2])");
        org.apache.dubbo.common.extension.ext1.SimpleExt extension = (org.apache.dubbo.common.extension.ext1.SimpleExt) ExtensionLoader.getExtensionLoader(org.apache.dubbo.common.extension.ext1.SimpleExt.class).getExtension(extName);
        return extension.yell(arg0, arg1);
    }

    public java.lang.String bang(org.apache.dubbo.common.URL arg0, int arg1) {
        throw new UnsupportedOperationException("The method public abstract java.lang.String org.apache.dubbo.common.extension.ext1.SimpleExt.bang(org.apache.dubbo.common.URL,int) of interface org.apache.dubbo.common.extension.ext1.SimpleExt is not adaptive method!");
    }
}

```

### 导出服务的过程
1. 使用Spring容器时，ServiceBean实现ApplicationListener接口，在ContextRefreshedEvent事件执行时，导出服务
2. 根据配置，服务也可能延迟导出或者不导出
3. ServiceBean继承ServiceConfig类，通过export方法导出服务
4. 加载注册中心URL，获取注册中心连接后，调用doExportUrlsFor1Protocol(ProtocolConfig,List<RegistryUrl>)
5. 导出本地服务与远程服务，通过scope配置
6. 远程服务到处过程中，先为服务提供类(ref)生成 Invoker，再导出远程服务，并生成 Exporter
7. 包含了服务注册与服务导出两个过程
调用ProtocolFilterWrapper是Protocol的Wrapper类，调用协议export方法执行时传入的Url对象被包装为
registry://127.0.0.1:9090/org.apache.dubbo.registry.RegistryService?application=app&dubbo=2.0.2&export=mockprotocol2://10.10.169.16:60009/org.apache.dubbo.config.api.DemoService?anyhost=true&application=app&bind.ip=10.10.169.16&bind.port=60009&default.deprecated=false&default.dynamic=false&default.export=true&default.register=true&deprecated=false&dubbo=2.0.2&dynamic=false&echo.0.callback=false&export=true&generic=false&interface=org.apache.dubbo.config.api.DemoService&methods=sayName,getUsers,echo,throwDemoException,getBox&pid=11200&register=true&release=&side=provider&timestamp=1559726306459&pid=11200&registry=mockprotocol2&timestamp=1559726305691
先通过RegistryProtocol协议注册服务，调用RegistryProtocol中的doLocalExport方法暴露远程服务
```java
private <T> ExporterChangeableWrapper<T> doLocalExport(final Invoker<T> originInvoker, URL providerUrl) {
        String key = getCacheKey(originInvoker);

        return (ExporterChangeableWrapper<T>) bounds.computeIfAbsent(key, s -> {
            Invoker<?> invokerDelegete = new InvokerDelegate<>(originInvoker, providerUrl);
            return new ExporterChangeableWrapper<>((Exporter<T>) protocol.export(invokerDelegete), originInvoker);
        });
    }
```
8. DubboProtocol中openServer方法开打服务端，暴露服务端口

### 服务引入

Invoker 是 Dubbo 的核心模型，代表一个可执行体。在服务提供方，Invoker 用于调用服务提供类。在服务消费方，Invoker 用于执行远程调用。
```java
package org.apache.dubbo.rpc;

import org.apache.dubbo.common.Node;

/**
 * Invoker. (API/SPI, Prototype, ThreadSafe)
 *
 * @see org.apache.dubbo.rpc.Protocol#refer(Class, org.apache.dubbo.common.URL)
 * @see org.apache.dubbo.rpc.InvokerListener
 * @see org.apache.dubbo.rpc.protocol.AbstractInvoker
 */
public interface Invoker<T> extends Node {

    /**
     * get service interface.
     *
     * @return service interface.
     */
    //获取提供者接口
    Class<T> getInterface();

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws RpcException
     */
    //执行方法，返回消费者调用结果
    Result invoke(Invocation invocation) throws RpcException;

}
```
消费者url被包装为
registry://224.5.6.7:1234/org.apache.dubbo.registry.RegistryService?application=test-protocol-random-port&dubbo=2.0.2&pid=13164&refer=application=test-protocol-random-port&check=true&default.check=true&default.init=false&default.injvm=false&default.lazy=false&default.sticky=false&dubbo=2.0.2&init=false&injvm=false&interface=org.apache.dubbo.config.api.DemoService&lazy=false&methods=getUsers,sayName,echo,getBox,throwDemoException&pid=13164&register.ip=10.10.169.16&scope=remote&side=consumer&sticky=false&timestamp=1559787738061&registry=multicast&timestamp=1559787738062


ReferenceBean继承InitializingBean，根据Spring的特性，dubbo:reference 的init属性为false时，消费者延迟加载
ReferenceConfig中getObject方法，最终调用init方法，创建代理类，生成Invoker，并通过refprotocol.refer创建Client与服务端进行连接
配置connections为0时，消费端共享Client
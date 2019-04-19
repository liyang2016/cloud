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
# kafka
### 基本概念
>1. Kafka作为一个集群，运行在一台或者多台服务器上。
>2. Kafka 通过 topic 对存储的流数据进行分类。
>3. 每条记录中包含一个key，一个value和一个timestamp（时间戳）。
### 基本四个组件
>1. The Producer API 允许一个应用程序发布一串流式的数据到一个或者多个Kafka topic。
>2. The Consumer API 允许一个应用程序订阅一个或多个 topic ，并且对发布给他们的流式数据进行处理。
>3. The Streams API 允许一个应用程序作为一个流处理器，消费一个或者多个topic产生的输入流，然后生产一个输出流到一个或多个topic中去，在输入输出流中进行有效的转换。
>4. The Connector API 允许构建并运行可重用的生产者或者消费者，将Kafka topics连接到已存在的应用程序或者数据系统。比如，连接到一个关系型数据库，捕捉表（table）的所有变更内容。
---------
![kafka-api](./img/kafka-apis.png)


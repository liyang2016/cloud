package com.leon.cloud.common.uilts;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

@Log4j2
public class ZkClient {

    private static CuratorFramework curatorFramework;

    private static void setCuratorFramework(CuratorFramework client) {
        curatorFramework=client;
    }

    /**
     * 创建zk客户端
     *
     * @return
     */
    private static CuratorFramework getClient(ZkConnectProperties zkConnectProperties) {
        if (curatorFramework != null && curatorFramework.getState().equals(CuratorFrameworkState.STARTED)) {
            log.info("curatorFramework exists");
            return curatorFramework;
        } else {
            //每秒尝试连接，重试最大次数3次，默认session超时时间为1分钟，connection超时时间为15秒
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            String id = zkConnectProperties.getAuthority();
            String connection=zkConnectProperties.getBackupAddress();
            String AUTH_SCHEME = "auth";
            String DIGEST_SCHEME = "digest";
            //权限控制
            ACLProvider aclProvider = new ACLProvider() {
                private List<ACL> acl;

                @Override
                public List<ACL> getDefaultAcl() {
                    if (acl == null) {
                        ArrayList<ACL> acl = ZooDefs.Ids.CREATOR_ALL_ACL;
                        acl.clear();
                        acl.add(new ACL(ZooDefs.Perms.ALL, new Id(AUTH_SCHEME, id)));
                        this.acl = acl;
                    }
                    return acl;
                }

                @Override
                public List<ACL> getAclForPath(String path) {
                    return acl;
                }
            };

            //默认connection超时时间为15秒
            int connectionTimeoutMs = zkConnectProperties.getTimeout();
            String namespace = zkConnectProperties.getNamespace();

            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().aclProvider(aclProvider).
                    connectionTimeoutMs(connectionTimeoutMs).
                    connectString(connection).
                    namespace(namespace).
                    retryPolicy(retryPolicy);
            if(id!=null){
                builder.authorization(DIGEST_SCHEME, id.getBytes());
            }
            CuratorFramework client=builder.build();
            client.start();
            setCuratorFramework(client);
            client.getConnectionStateListenable().addListener((curatorFramework, newState) -> {
                if (newState == ConnectionState.LOST) {
                    log.info("zk lost");
                } else if (newState == ConnectionState.CONNECTED) {
                    log.info("zk connect");
                } else if (newState == ConnectionState.RECONNECTED) {
                    log.info("zk reconnected  {}");
                }
            });

            return client;
        }
    }

    public static List<String> addTargetChildListener(String path, CuratorWatcher listener) {
        try {
            return curatorFramework.getChildren().usingWatcher(listener).forPath(path);
        } catch (KeeperException.NoNodeException e) {
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static CuratorFramework getClient(){
        if(curatorFramework != null && curatorFramework.getState().equals(CuratorFrameworkState.STARTED)){
            return curatorFramework;
        }else{
            Properties properties=new Properties();
            //默认读取classpath下zk.properties文件
            String path = Objects.requireNonNull(ClassPathResource.class.getClassLoader().getResource("zk.properties")).getPath();
            File file=new File(path);
            if (file.exists()) {
                try (InputStream in = new FileInputStream(file)) {
                    properties.load(in);
                    if (log.isInfoEnabled()) {
                        log.info("Load service store file " + file + ", data: " + properties);
                    }
                } catch (Throwable e) {
                    log.warn("Failed to load service store file " + file, e);
                }
            }
            ZkConnectProperties zkConnectProperties=loadProperties(properties);
            return getClient(zkConnectProperties);
        }

    }

    private static ZkConnectProperties loadProperties(Properties properties) {
        ZkConnectProperties zkConnectProperties=new ZkConnectProperties();
        zkConnectProperties.setUrl(properties.getProperty("url"));
        return zkConnectProperties;
    }


    /**
     * @param client
     * @param parentPath
     * @throws Exception
     */
    private static void nodesList(CuratorFramework client, String parentPath) throws Exception {
        List<String> paths = client.getChildren().forPath(parentPath);
        paths.forEach(log::info);
    }


    private static void createNode(CuratorFramework client, String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        log.info(stat);
        String forPath = client.create().creatingParentsIfNeeded().forPath(path, "create init !".getBytes());
        log.info(forPath);
    }


    private static void getDataNode(CuratorFramework client, String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        log.info(stat);
        byte[] dates = client.getData().forPath(path);
        log.info(dates);
    }

    private static void setDataNode(CuratorFramework client, String path, String message) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        log.info(stat);
        client.setData().forPath(path, message.getBytes());
    }

    private static void deleteDataNode(CuratorFramework client, String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        log.info("deleteNode : {}", stat);
        Void forPath = client.delete().deletingChildrenIfNeeded().forPath(path);
        log.info(forPath);
    }


    public static void addWatch(CuratorFramework client,CuratorListener listen){
        client.getCuratorListenable().addListener(listen);
    }

    public static class CuratorWatcherImpl implements CuratorWatcher {

        private volatile ChildListener listener;

        public CuratorWatcherImpl(ChildListener listener) {
            this.listener = listener;
        }

        public void unwatch() {
            this.listener = null;
        }

        @Override
        public void process(WatchedEvent event) throws Exception {
            if (listener != null) {
                String path = event.getPath() == null ? "" : event.getPath();
                listener.childChanged(path,
                        // if path is null, curator using watcher will throw NullPointerException.
                        // if client connect or disconnect to server, zookeeper will queue
                        // watched event(Watcher.Event.EventType.None, .., path = null).
                        StringUtils.isNotEmpty(path)
                                ? curatorFramework.getChildren().usingWatcher(this).forPath(path)
                                : Collections.<String>emptyList());
            }
        }
    }


    public static void main(String[] args) {
        try {
//            nodesList(getClient("127.0.0.1", "esop", "asiainfo$123"), "/dubbo/org.apache.dubbo.demo.DemoService");
            nodesList(getClient(),"/dubbo/org.apache.dubbo.demo.DemoService/providers");
            addTargetChildListener("/dubbo/org.apache.dubbo.demo.DemoService/providers", new CuratorWatcherImpl((path, children) -> {
                log.info("children data changed");
                log.info("path:{},children:{}",path,children);
            }));
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

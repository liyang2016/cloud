package com.leon.cloud.common.uilts;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ZkClient {

    private static final Logger logger = LoggerFactory.getLogger(ZkClient.class);

    /**
     * 创建zk客户端
     *
     * @return
     */
    private static CuratorFramework getClient(String connection, String username, String password) {
        //每秒尝试连接，重试最大次数3次，默认session超时时间为1分钟，connection超时时间为15秒
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        String id = username + ":" + password;
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
        int connectionTimeoutMs = 5000;
        String namespace = "";

        CuratorFramework client = CuratorFrameworkFactory.builder().aclProvider(aclProvider).
                authorization(DIGEST_SCHEME, id.getBytes()).
                connectionTimeoutMs(connectionTimeoutMs).
                connectString(connection).
                namespace(namespace).
                retryPolicy(retryPolicy).build();
        client.start();

        return client;
    }

    private static CuratorFramework getClient(String connection) {
        return getClient(connection, "", "");
    }


    /**
     * @param client
     * @param parentPath
     * @throws Exception
     */
    private static void nodesList(CuratorFramework client, String parentPath) throws Exception {
        List<String> paths = client.getChildren().forPath(parentPath);
        for (String path : paths) {
            logger.info(path);
        }
    }


    private static void createNode(CuratorFramework client, String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        System.out.println(stat);
        String forPath = client.create().creatingParentsIfNeeded().forPath(path, "create init !".getBytes());
        System.out.println(forPath);
    }


    private static void getDataNode(CuratorFramework client, String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        System.out.println(stat);
        byte[] datas = client.getData().forPath(path);
        System.out.println(new String(datas));
    }

    private static void setDataNode(CuratorFramework client, String path, String message) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        System.out.println(stat);
        client.setData().forPath(path, message.getBytes());
    }

    private static void deleteDataNode(CuratorFramework client, String path) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        System.out.println("deleteNode : " + stat);
        Void forPath = client.delete().deletingChildrenIfNeeded().forPath(path);
        System.out.println(forPath);
    }

    public static void main(String[] args) {
        try {
            nodesList(getClient("127.0.0.1","esop","asiainfo$123"), "/dubbo/org.apache.dubbo.demo.DemoService/providers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

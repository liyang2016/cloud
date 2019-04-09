### 客户端
#### znode节点包含信息
>>> 每个 znode 的状态信息包含以下内容：
>>> czxid，创建（create）该 znode 的 zxid
>>> mzxid，最后一次修改（modify）该 znode 的 zxid
>>> pzxid，最后一次修改该 znode 子节点的 zxid
>>> ctime，创建该 znode 的时间
>>> mtime，最后一次修改该 znode 的时间
>>> dataVersion，该节点内容的版本，每次修改内容，版本都会增加
>>> cversion，该节点子节点的版本
>>> aclVersion，该节点的 ACL 版本
>>> ephemeralOwner，如果该节点是临时节点（ephemeral node），会列出该节点所在客户端的 session id；如果不是临时节点，该值为 0
>>> dataLength，该节点存储的数据长度
>>> numChildren，该节点子节点的个数
```shell
ZooKeeper -server host:port cmd args
        stat path [watch]
        set path data [version]
        ls path [watch]
        delquota [-n|-b] path
        ls2 path [watch]
        setAcl path acl
        setquota -n|-b val path
        history
        redo cmdno
        printwatches on|off
        delete path [version]
        sync path
        listquota path
        rmr path
        get path [watch]
        create [-s] [-e] path data acl
        addauth scheme auth
        quit
        getAcl path
        close
        connect host:port
        

#设置权限 用户名密码
addauth digest esop:asiainfo$123
ls /dubbo
get /dubbo

#删除节点 rmr可以删除存在子目录的节点 delete删除不存在子目录的节点，也可以删除指定的版本
rmr /curator
delete /curator [version]

#创建节点 -S 或 -E 分别指节点顺序、临时，默认持久节点；acl权限控制
create [-s] [-e] path data acl
#修改节点 -data就是要更新的新内容，version表示数据版本
set path data [version]

```

### curator framework

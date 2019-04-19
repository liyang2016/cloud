package com.leon.cloud.common.uilts;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class ZkConnectProperties {

    private String url;

    private int port;

    private String username;

    private String password;

    private int timeout;

    private String namespace;

    public String getBackupAddress() {
        return port <= 0 ? url : url + ":" + port;
    }

    public String getAuthority() {
        if (StringUtils.isEmpty(username)
                && StringUtils.isEmpty(password)) {
            return null;
        }
        return (username == null ? "" : username)
                + ":" + (password == null ? "" : password);
    }

}

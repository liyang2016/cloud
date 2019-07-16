package com.leon.cloud.common.uilts;

import java.util.List;


/**
 * 子节点变化监听
 */
public interface ChildListener {

    void childChanged(String path, List<String> children);
}

package com.leon.cloud.common.design.observer;


public class JDKObserver {

    public static void main(String[] args) {
        Publish publish=new Publish();
        new Subscribe(publish);
        publish.setData("开始");
    }
}

package com.leon.cloud.common.design.observer.custom;


/**
 * 观察者
 */
public class Subscriber implements ISubscriber{
    @Override
    public void update() {
        System.out.println("收到通知");
    }
}

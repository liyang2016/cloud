package com.leon.cloud.common.design.adapter;

public class Main {

    public static void main(String[] args) {
        HandlerAdapter adapter = new BeanNameHandlerAdapter();
        BeanNameHandlerMapping handlerMapping = new BeanNameHandlerMapping();
        Object handler = handlerMapping.getHandler();
        System.out.println(adapter.handler(handler));
    }
}

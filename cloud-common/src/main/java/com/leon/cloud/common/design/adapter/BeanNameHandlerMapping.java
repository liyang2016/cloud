package com.leon.cloud.common.design.adapter;

public class BeanNameHandlerMapping {

    private HandlerAdapter handlerAdapter;
    private Object handler;

    public BeanNameHandlerMapping() {
        this.handlerAdapter = new BeanNameHandlerAdapter();
        this.handler = new BeanNameHandler();
    }

    Object getHandler() {
        if (handlerAdapter.support(handler)) {
            return handler;
        }
        return null;
    }


    public static void main(String[] args) {
        HandlerAdapter adapter = new BeanNameHandlerAdapter();
        BeanNameHandlerMapping handlerMapping = new BeanNameHandlerMapping();
        Object handler = handlerMapping.getHandler();
        System.out.println(adapter.handler(handler));
    }
}

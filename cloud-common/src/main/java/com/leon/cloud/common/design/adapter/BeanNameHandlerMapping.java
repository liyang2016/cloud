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
}

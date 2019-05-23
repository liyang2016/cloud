package com.leon.cloud.common.design.adapter;

public class BeanNameHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return handler instanceof BeanNameHandler;
    }

    @Override
    public Object handler(Object handler) {
        return ((BeanNameHandler) handler).handler();
    }
}

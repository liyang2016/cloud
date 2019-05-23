package com.leon.cloud.common.design.adapter;

public interface HandlerAdapter {

     boolean support(Object handler);

     Object handler(Object handler);
}

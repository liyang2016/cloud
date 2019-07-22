package com.leon.cloud.common.design.produce_consumer.product;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Data {

    public static final BlockingQueue<String> product=new ArrayBlockingQueue<>(10);
}

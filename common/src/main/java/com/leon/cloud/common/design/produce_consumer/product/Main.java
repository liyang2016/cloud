package com.leon.cloud.common.design.produce_consumer.product;

public class Main {

    public static void main(String[] args) {
        Consumer consumer=new Consumer();
        Producer producer=new Producer();

        consumer.start();
        producer.start();

    }
}

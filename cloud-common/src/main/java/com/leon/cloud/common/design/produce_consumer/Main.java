package com.leon.cloud.common.design.produce_consumer;

public class Main {

    public static void main(String[] args) {

        Thread consumerThread=new Thread(new Consumer());
        Thread producerThread=new Thread(new Producer());

        consumerThread.start();
        producerThread.start();


        try {
            consumerThread.join();
            producerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("running");
    }
}

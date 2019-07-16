package com.leon.cloud.common.concurrent.countDownLatch;

public class CountDownLatchExample {


    public static void main(String[] args) {
        Drive drive=new Drive();
        try {
            drive.main();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

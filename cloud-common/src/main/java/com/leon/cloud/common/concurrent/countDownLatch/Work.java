package com.leon.cloud.common.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Work implements Runnable {
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    Work(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal=startSignal;
        this.doneSignal=doneSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        System.out.println("doWork");
    }
}

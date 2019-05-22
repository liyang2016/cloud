package com.leon.cloud.common.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

class Drive {

    void main() throws InterruptedException {
        int size = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(size);

        for (int i = 0; i < size; i++) new Thread(new Work(startSignal, doneSignal)).start();

        doSomethingElse(1);            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse(2);
        doneSignal.await();           // wait for all to finish
    }

    private void doSomethingElse(int i) {
        System.out.println("doSomethingElse" + i);
    }
}

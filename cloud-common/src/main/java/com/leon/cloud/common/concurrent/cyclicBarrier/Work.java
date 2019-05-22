package com.leon.cloud.common.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Work implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private int index;
    Work(CyclicBarrier cyclicBarrier,int i) {
        this.cyclicBarrier = cyclicBarrier;
        this.index=i;
    }

    @Override
    public void run() {
        try {
            System.out.println("waiting"+index);
            cyclicBarrier.await();
            doWork();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("done"+index);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        System.out.println("working"+index);
    }
}

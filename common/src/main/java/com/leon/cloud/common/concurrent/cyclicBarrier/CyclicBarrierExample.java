package com.leon.cloud.common.concurrent.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("barrierCommand working"));

        for (int i = 0; i < 12; i++) {
            TimeUnit.SECONDS.sleep(2);
            new Thread(new Work(cyclicBarrier, i)).start();
        }

    }
}

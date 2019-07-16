package com.leon.cloud.common.concurrent.semaphore;

public class SemaphoreExample {


    public static void main(String[] args) throws InterruptedException {
        int size = 10;
        Object[] items = new Object[size];
        for (int i = 0; i < size; i++) {
            items[i] = new Book(i);
        }
        Pool pool = new Pool(items);
        Object o;
        for (int i = 0; i < size - 1; i++) {
            System.out.println(pool.getItem());
        }
        o=pool.getItem();
        pool.putItem(o);

        System.out.println(pool.getItem());
    }
}

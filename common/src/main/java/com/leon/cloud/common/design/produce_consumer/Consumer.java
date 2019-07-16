package com.leon.cloud.common.design.produce_consumer;

public class Consumer implements Runnable {
    @Override
    public void run() {
        while (true) {
            synchronized (Product.list) {
                while (Product.list.size() == 0) {
                    try {
                        //释放Product.list共享变量锁
                        //被唤醒时要继续向下执行必须重新获得共享变量的锁
                        Product.list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("remove item"+Product.list.get(0));
                Product.list.remove(0);
                Product.list.notifyAll();
            }
        }
    }
}

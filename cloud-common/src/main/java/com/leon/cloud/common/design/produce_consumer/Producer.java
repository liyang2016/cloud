package com.leon.cloud.common.design.produce_consumer;

public class Producer implements Runnable{
    @Override
    public void run() {
        while(true){
            synchronized (Product.list){
                while (Product.list.size()==Product.MAX_COUNT){
                    try {
                        Product.list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Product.list.add("1");
                System.out.println("produce:"+Product.list.get(Product.list.size()-1));
                Product.list.notifyAll();
            }
        }
    }
}

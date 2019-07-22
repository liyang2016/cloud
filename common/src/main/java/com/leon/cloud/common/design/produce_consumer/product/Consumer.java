package com.leon.cloud.common.design.produce_consumer.product;

public class Consumer extends Thread {

    public void run() {
//        while (true){
//            synchronized (Data.product){
//                if(Data.product.size()==0){
//                    try {
//                        Data.product.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println(Data.product.poll());
//                System.out.println("consumer");
//                Data.product.notifyAll();
//
//            }
//        }
        while (true) {
            if (Data.product.size() > 0) {
                try {
                    String s = Data.product.take();
                    System.out.println("consumer:" + s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

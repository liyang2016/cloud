package com.leon.cloud.common.design.produce_consumer.product;

public class Producer extends Thread {


    public void run() {
//        while (true) {
//            synchronized (Data.product) {
//                if (Data.product.size() >= 10) {
//                    try {
//                        Data.product.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Data.product.offer("product");
//                System.out.println("produce");
//                Data.product.notifyAll();
//            }
//
//        }


        while (true){
            if(Data.product.size()<10){
                try {
                    Data.product.put("product");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("product");

            }
        }
    }

}

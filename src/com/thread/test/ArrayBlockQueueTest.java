package com.thread.test;

import sun.jvm.hotspot.opto.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockQueueTest {

    //定义一个阻塞队列，默认的Capacity为3
    private static final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);


//    public static void main(String[] args) {
//
//        new Thread(new Producer(blockingQueue)).start();
//        new Thread(new Consumer(blockingQueue)).start();
//
//    }


}

class Producer implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                String productionName = "production" + i;
                System.out.println("生产出了:" + productionName);
                this.blockingQueue.put(productionName);
            }
            System.out.println("全部添加完毕啦");
            this.blockingQueue.add("stop");
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String msg = "";
        try {
            Thread.sleep(1000);
            while (!(msg = blockingQueue.take()).equals("stop")) {
                System.out.println("消费到了:" + msg);
            }
            System.out.println("全部消费结束啦");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


package com.thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//演示CountDownLatch控制所有线程同时结束执行之后代码的功能

public class CountdownLatchTest implements Runnable {
    private static final CountDownLatch countDownLatch = new CountDownLatch(5);

//    public static void main(String[] args) {
//
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(new CountdownLatchTest());
//        }
//
//        if (countDownLatch.getCount() == 0) {
//            System.out.println("所有人吃完饭啦");
//        }
//    }


    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + ",开始吃饭");

            long costTime = (long) (Math.random() * 3 * 1500);
            Thread.sleep(costTime);
            System.out.println(Thread.currentThread().getName() + ",吃饭花了" + costTime + "毫秒时间");
            //计算器减少1个
            countDownLatch.countDown();
            countDownLatch.await();

            System.out.println(Thread.currentThread().getName() + ",吃完饭啦");


        } catch (Exception e) {

        } finally {
//            System.out.println("进入了final");
        }

    }
}

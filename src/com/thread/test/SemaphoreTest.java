package com.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//使用信号量，可以控制同时并发执行某个"重服务"执行的数量。这样来控制系统的负荷
public class SemaphoreTest implements Runnable {

    private static final Semaphore semphore = new Semaphore(3);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 20; i++) {
            executorService.submit(new SemaphoreTest());
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        try {
            semphore.acquire();
            System.out.println(Thread.currentThread().getName() + ",获取到了信号量，开始执行耗时消耗资源的Task");
            Thread.sleep(3000);
            semphore.release();
        } catch (Exception e) {

        }
    }
}

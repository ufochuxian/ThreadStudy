package com.thread.test;

public class ThreadTest {


    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ",主线程执行开始");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //这里并不会
                    //在java里面，子线程发送异常，并不会导致主进程崩溃。但是在android中，由于android实现了
                    //默认的发生异常的处理的handler，然后kill掉主进程，所以会结束主进程
                    //https://blog.csdn.net/yuzhangzhen/article/details/111399942
                    Thread.sleep(3000);
                    throw new RuntimeException("子线程发生了异常");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "，子线程休息了3秒钟后，开始执行");
            }
        }).start();

        System.out.println(Thread.currentThread().getName() + "主线程执行逻辑完成，进入等待");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + "主线程休息完，结束啦");

    }


}

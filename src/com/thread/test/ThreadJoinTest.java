package com.thread.test;

public class ThreadJoinTest {


    //通过thread join也能够实现，两个线程一起执行完成任务
    static Thread thread2 = new Thread(new Runnable2());

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable1());
        thread1.start();
        thread2.start();

    }


    static class Runnable1 implements Runnable {

        @Override
        public void run() {
            System.out.println("线程一准备执行工作");
            try {
                Thread.sleep(3000);
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程一执行完毕工作");
        }
    }

    static class Runnable2 implements Runnable {

        @Override
        public void run() {
            System.out.println("线程二准备执行工作");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程二执行完毕工作");
        }
    }


}



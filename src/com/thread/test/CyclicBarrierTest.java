package com.thread.test;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    //cyclicBarrier和CountDownLatch的区别是，cyclicBarrier在执行后，可以去继续进行执行

    private static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {

        cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有任务都完成了，继续进行下次操作");
            }

        });
        new Thread(new Task1()).start();
        new Thread(new Task2(cyclicBarrier)).start();

    }


    static class Task1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep((long) (Math.random() * 20000));
                System.out.println( "任务一,执行完成了");
                cyclicBarrier.await();
                //这部分的代码会在cyclicBarrier的run方法执行之后，继续去进行执行
                System.out.println("任务一，继续do Somthing");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    static class Task2 implements Runnable {

        private CyclicBarrier cyclicBarrier;

        public Task2(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((long) (Math.random() * 20000));
                System.out.println("任务二执行完成了");
                this.cyclicBarrier.await();
                System.out.println("任务二，继续do Somthing");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

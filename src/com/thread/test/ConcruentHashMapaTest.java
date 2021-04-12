package com.thread.test;

import java.util.concurrent.ConcurrentHashMap;

public class ConcruentHashMapaTest {

    public  static ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        hashMap.put("value",0);
        Thread thread1 = new Thread(new CalRunnable());
        Thread thread2 = new Thread(new CalRunnable());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("hashMap:" + hashMap.get("value"));
    }


    static class CalRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 1000; i++) {
                //这里如果不加上synchronized关键字的话，那么即便使用CorrcurrentHashMap，那么也不是线程安全的
                //因为CorrcurrentHashMap只能保证单独的get,put的线程安全，无法保证一组操作的线程安全。
//                hashMap.put("value", value+1);
                //这里如果不使用锁，那么可以使用ConcruentHashMap的replace，这个方法可以保证原子性操作
                while (true) {
                    Integer value = hashMap.get("value");
                    int newValue = value + 1;
                    String threadName = Thread.currentThread().getName();
                    System.out.println("value:" + value + ",newValue" + newValue + ",threadName:" + threadName);
                    boolean updateValueSuccess = hashMap.replace("value", value, newValue);
                    if (updateValueSuccess) {
                        //replace方法，会有一个返回值，只有保证更新成功了，那么退出。否则会等待，再次尝试更新
                        break;
                    }
                }
            }

        }

        public void print() {
            System.out.println("i的值:" + hashMap.get("value"));
        }
    }


}



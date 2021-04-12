package com.thread.test;

import java.util.concurrent.ConcurrentHashMap;

public class ConcruentHashMapaTest {

    public static void main(String[] args) throws InterruptedException {

        CalRunnable calRunnable = new CalRunnable();
        Thread thread1 = new Thread(calRunnable);
        Thread thread2 = new Thread(calRunnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        calRunnable.print();

    }


}


class CalRunnable implements Runnable {

    ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            //这里如果不加上synchronized关键字的话，那么即便使用CorrcurrentHashMap，那么也不是线程安全的
            //因为CorrcurrentHashMap只能保证单独的get,put的线程安全，无法保证一组操作的线程安全。
            synchronized (this) {
                Integer value = hashMap.get("value");
                if(value == null) {
                    value = 0;
                }
                String threadName = Thread.currentThread().getName();
                System.out.println("value:" + value + ",threadName:" + threadName);
                hashMap.put("value", value+1);
            }
        }

    }

    public void print() {
        System.out.println("i的值:" + hashMap.get("value"));
    }
}
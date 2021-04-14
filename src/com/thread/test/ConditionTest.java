package com.thread.test;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final int queueSize = 10;

    private static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(queueSize);

    public static void main(String[] args) {
        Condition consumerCondition = lock.newCondition();
        Condition producerCondition = lock.newCondition();

//        Executors.newFixedThreadPool(50).submit(new ProductConsumer(consumerCondition, producerCondition));
//        Executors.newFixedThreadPool(50).submit(new ProductProducer(consumerCondition, producerCondition));

        new Thread(new ProductProducer(consumerCondition, producerCondition)).start();
        new Thread(new ProductConsumer(consumerCondition, producerCondition)).start();

    }


    static class ProductConsumer implements Runnable {

        private Condition consumerCondition;

        private Condition producerCondition;

        public ProductConsumer(Condition consumerCondition, Condition producerCondition) {
            this.consumerCondition = consumerCondition;
            this.producerCondition = producerCondition;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();

                    while (priorityQueue.size() == 0) {
                        System.out.println("产品队列现在已经没有了，阻塞消费线程，暂时不消费");
//                    producerCondition.signalAll();
                        consumerCondition.await();
                        System.out.println("消费者---------状态");
                    }
                    //如果队列没有满，那么就去进行生产
                    Thread.sleep(1000);
                    //peek方法，不会改变queueSize已有容量大小，看源码。poll方法会进行更改
                    Integer pool = priorityQueue.poll();
                    System.out.println("消费了,poll" + pool + ",还剩下:" + priorityQueue.size());
                    producerCondition.signalAll();

                } catch (Exception e) {
                    System.out.println(e.getCause());

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ProductProducer implements Runnable {

        private Condition consumerCondition;

        private Condition producerCondition;

        public ProductProducer(Condition consumerCondition, Condition produceCondition) {
            this.consumerCondition = consumerCondition;
            this.producerCondition = produceCondition;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (priorityQueue.size() == queueSize) {
                        System.out.println("产品队列现在已经满了，阻塞生产线程，暂时不生产");
                        producerCondition.await();
                        System.out.println("生产者---------状态");
                    }
                    //队列现在不满，唤醒
                    Thread.sleep(800);
                    //add和offer的区别，和peek和poll的区别是类似的
                    boolean offer = priorityQueue.offer(1);
                    System.out.println("生产啦," + offer + "队列大小" + priorityQueue.size());
                    consumerCondition.signalAll();

                } catch (Exception e) {
                    System.out.println(e.getCause());

                } finally {
                    lock.unlock();
                }
            }

        }
    }

}



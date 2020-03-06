package com.snake.lirang;

/**
 * @auther: snake
 * @date: 2020/3/4 23:16
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 高聚低合前提下,线程操作资源类
 * 2. 判断/干活/通知
 * 3. 多线程交互中,必须要防止多线程的虚假唤醒,也即
 * 4.
 */

// 多个线程 交替 1  0  1 0
// wait notify 交替
public class ThreadWaitNotifyDemo3 {
    public static void main(String[] args) {
        AirConditioner3 airConditioner = new AirConditioner3();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {

                    airConditioner.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}

//资源类
class AirConditioner3 {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {

            //1. 判断
            while (number != 0) {
                condition.await();
            }
            //2. 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3. 通知
            condition.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {

            //1. 判断
            while (number == 0) {
                condition.await();
            }
            //2. 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3. 通知
            condition.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

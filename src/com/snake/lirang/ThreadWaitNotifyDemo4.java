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
 * 4. 标志位
 */

// 题目:  a  b  c 循环打印
// await signal 交替
public class ThreadWaitNotifyDemo4 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            shareResource.print5(5, 1, 2);
        }, "A").start();
        new Thread(() -> {
            shareResource.print10(10, 2, 3);
        }, "B").start();
        new Thread(() -> {
            shareResource.print15(15, 3, 1);
        }, "C").start();
    }
}

//资源类
class ShareResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public void print5(int num, int currentNumber, int nextNumber) {
        lock.lock();
        try {
            // 1.判断
            while (number != 1) {
                condition1.await();
            }

            // 2.干活
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3.通知
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int num, int currentNumber, int nextNumber) {
        lock.lock();
        try {
            // 1.判断
            while (number != 2) {
                condition2.await();
            }

            // 2.干活
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3.通知
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int num, int currentNumber, int nextNumber) {
        lock.lock();
        try {
            // 1.判断
            while (number != 3) {
                condition3.await();
            }

            // 2.干活
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3.通知
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

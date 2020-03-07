package com.snake.lirang;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snake
 * on 2020/3/5 15:41.
 */
public class ThreadTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        Resources resources = new Resources();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resources.print(lock, 1, conditionA, conditionB);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resources.print(lock, 2, conditionB, conditionC);

            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resources.print(lock, 3, conditionC, conditionA);

            }
        }, "C").start();
    }
}

class Resources {

    void print(Lock lock, int num, Condition currentCondition, Condition nextCondition) {
        lock.lock();
        try {


            for (int i = 0; i < num; i++) {
                System.out.println(Thread.currentThread().getName() + "\t");
            }
            nextCondition.signal();

            currentCondition.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

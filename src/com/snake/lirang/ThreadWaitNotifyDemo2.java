package com.snake.lirang;

/**
 * @auther: snake
 * @date: 2020/3/4 23:16
 */

/**
 * 1. 高聚低合前提下,线程操作资源类
 * 2. 判断/干活/通知
 * 3. 多线程交互中,必须要防止多线程的虚假唤醒,也即(判断只能用while,不能用if)
 */

// 多个线程 交替 1  0  1 0
// 礼让,wait notify 交替
public class ThreadWaitNotifyDemo2 {
    public static void main(String[] args) {
        AirConditioner2 airConditioner = new AirConditioner2();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(200);
                    airConditioner.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(400);
                    airConditioner.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

//资源类
class AirConditioner2 {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {

        //1. 判断
        while (number != 0) {
            this.wait();
        }
        //2. 干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3. 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {

        //1. 判断
        while (number == 0) {
            this.wait();
        }
        //2. 干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3. 通知
        this.notifyAll();
    }
}

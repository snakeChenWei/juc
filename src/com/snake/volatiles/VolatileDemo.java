package com.snake.volatiles;

/**
 * @auther: snake
 * @date: 2020/3/8 00:03
 */

// volatile 关键字
// 优势: 保证内存中的数据可见性.
// 缺点: 1. 不具备 互斥性 2. 不能保证原子性.

public class VolatileDemo {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("--------main--------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {
    public boolean isFlag() {
        return flag;
    }

    private volatile boolean flag = false;

    @Override
    public void run() {
        flag = true;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(flag + "------------");
    }
}
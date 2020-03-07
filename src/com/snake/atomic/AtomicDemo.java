package com.snake.atomic;

/**
 * @auther: snake
 * @date: 2020/3/8 00:13
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一. i++的原子性问题: i++ 的操作实际上氛围三个步骤 "读-改-写"
 * int i = 10;
 * i = i++;  //10
 * <p>
 * int temp = i; 入栈
 * i = i+1; 自增1,元空间,
 * i = temp; 出栈,赋值.
 * <p>
 * 二. CAS (Compare-And-Swap) 算法保证数据的原子性
 * CAS 算法是硬件对于鬓发操作共享数据的支持
 * CAS 包含了三个操作数
 * 内存值 V
 * 预估值 A
 * 更新值 B
 * 当且仅当 V == A时, V=B, 否则,将不做任何操作
 */
public class AtomicDemo {
    public static void main(String[] args) {

        ThreadDemo threadDemo = new ThreadDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(threadDemo).start();
        }
    }
}

class ThreadDemo extends Thread {
    //    private int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.incrementAndGet();
    }
}

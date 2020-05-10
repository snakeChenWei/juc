package com.snake.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @auther: snake
 * @date: 2020/3/6 00:15
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await(1, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人");
    }
}

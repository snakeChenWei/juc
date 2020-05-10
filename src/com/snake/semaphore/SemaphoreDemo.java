package com.snake.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @auther: snake
 * @date: 2020/3/6 21:47
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("----进来了: " + Thread.currentThread().getName()+" "+System.currentTimeMillis());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("出来了: " + Thread.currentThread().getName());
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}

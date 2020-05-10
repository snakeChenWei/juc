package com.snake.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther: snake
 * @date: 2020/3/6 00:28
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(7,()->{
            System.out.println("-----一起召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t第"+ finalI +"颗");
                try {
                    // 等大家倒了
                    cyclicBarrier.await();
                    System.out.println("-----一起召唤神龙");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}

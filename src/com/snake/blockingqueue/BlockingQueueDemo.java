package com.snake.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @auther: snake
 * @date: 2020/3/6 21:53
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.offer("2"));
        System.out.println(blockingQueue.offer("3"));
        new Thread(()->{
            try {
                Thread.sleep(3000);
                System.out.println("-------睡3秒----出队");
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.poll());
                System.out.println(blockingQueue.remove());
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try {
                Thread.sleep(4000);
                System.out.println("-------睡4秒----入队");
                System.out.println(blockingQueue.add("4"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

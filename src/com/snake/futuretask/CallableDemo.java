package com.snake.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @auther: snake
 * @date: 2020/3/6 00:08
 */
// callable futureTask用法
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        System.out.println("----------come in main");

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();// 不会再进去了

        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-------进来了");
        return 1024;
    }
}

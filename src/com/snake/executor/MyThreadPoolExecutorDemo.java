package com.snake.executor;

import java.util.concurrent.*;

/**
 * @auther: snake
 * @date: 2020/3/6 22:26
 */
public class MyThreadPoolExecutorDemo {
    public static void main(String[] args) {
//        Executor executorService = new ThreadPoolExecutor();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println("该线程: " +
                        Thread.currentThread().getName() + "\t执行了任务: " + finalI);
            });
        }

        executorService.shutdown();
    }
}

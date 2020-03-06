package com.snake.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @auther: snake
 * @date: 2020/3/7 00:27
 */
// 异步回调

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回, update mysql ok");
        });
        System.out.println(completableFuture.get());


        CompletableFuture<Integer> insertCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回, insert mysql ok");
//            int i = 1 / 0;
            return 1024;
        });
        System.out.println(insertCompletableFuture.whenComplete((t, u) -> { // t 正常的返回值  u 是异常的类型
            System.out.println("******t:" + t);
            System.out.println("******u:" + u);
        }).exceptionally(f -> {
            System.out.println("******exception:" + f);
            return 4444;
        }).get());

    }
}

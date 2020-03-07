package com.snake.readWriteLock;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author snake
 * on 2020/3/6 09:29.
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {


        Resource resource = new Resource();
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> resource.write(finalI, String.valueOf(finalI)), String.valueOf(i)).start();
        }
        Thread.sleep(100);
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> resource.read(finalI), String.valueOf(i)).start();
        }
    }

}

class Resource {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    HashMap<Integer, String> hashMap = new HashMap<>();

    void write(Integer key, String value) {
        readWriteLock.writeLock().lock();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----写数据" + key);
        hashMap.put(key, value);
        System.out.println("----写完了");
        readWriteLock.writeLock().unlock();
    }

    void read(Integer key) {
        readWriteLock.readLock().lock();
        System.out.println("读数据");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("读完了 " + hashMap.get(key));
        readWriteLock.readLock().unlock();

    }
}

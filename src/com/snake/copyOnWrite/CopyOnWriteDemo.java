package com.snake.copyOnWrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @auther: snake
 * @date: 2020/3/8 01:03
 */
// copyOnWrite  写时复制: 添加操作时,效率低.   适合并发迭代操作多,使用.
public class CopyOnWriteDemo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        for (int i = 0; i < 10; i++) {
            new Thread(demo).start();
        }
    }
}

class Demo implements Runnable {
//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());
   private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("AA");
        }
    }
}
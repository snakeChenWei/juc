package com.snake.lamda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @auther: snake
 * @date: 2020/3/6 23:43
 */
// 4种类型函数式接口
public class InterfaceDemo {
    public static void main(String[] args) {
        // 消费型接口(有参数,没有返回值)
        Consumer<String> consumer = (s) -> { System.out.println(s); };
        Consumer<String> consumer3 = System.out::println;

        // 供给型接口(无参数,有返回值)
        Supplier<String> stringSupplier = () -> {return "双击666";};
        Supplier<String> stringSupplier2 = () -> "双击666";
        System.out.println(stringSupplier.get());

        // 函数型接口(有参数,有返回值)
        Function<String, Integer> function = (s) -> {return s.length();};
        Function<String, Integer> function3 = s-> s.length();
        Function<String, Integer> function4 = String::length;
        System.out.println(function.apply("牛逼的长度"));

        // 判断型接口(有参数,返回布尔值)
        Predicate<String> predicate = (s)->{return s.isEmpty();};
        Predicate<String> predicate2 = s-> s.isEmpty();
        Predicate<String> predicate3 = String::isEmpty;
        System.out.println(predicate2.test("牛逼"));
    }
}
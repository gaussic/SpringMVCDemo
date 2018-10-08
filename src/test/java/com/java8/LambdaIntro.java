package com.java8;

import java.util.List;
import java.util.function.Consumer;

public class LambdaIntro {
    /*public static <E> void eachWithIndex(List<E> list, ItemWithIndexVisitor<E> visitor) {
        for (int i = 0; i < list.size(); i++) {
            visitor.visit(list.get(i), i);
        }
    }*/


    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类方式");
            }
        });


        Thread thread1 = new Thread(()-> System.out.println("lambda表达式"));


        Consumer consumer = System.out::println;
        consumer.accept("方法引用");
        Thread thread2 = new Thread(System.out::println);


        thread.start();
        thread1.start();
        thread2.start();
    }
}

package com.java8;

import org.hibernate.boot.jaxb.SourceType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class TestLambda {
    public static void main(String[] args) {

        loopTest();
        List list = Arrays.asList("A", "B", "C", "D");
        eachWithIndex(list, (value, index) -> {
            String output = String.format("%d -> %s", index, value);
            System.out.println(output);
        });

        eachWithIndex(list, TestLambda::printItem);

        list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);

        List<Integer> listB = Arrays.asList(1, 1, 1, 1, 1, 1, 1);
        final BigDecimal total = listB.stream().map(i -> new BigDecimal(i)).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("total1:" + total);
        final BigDecimal total2 = listB.stream().map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("total2:  " + total2);

        list.forEach(n -> {
            int a = +1;
            System.out.println(n);
        });







        List<Integer> list3 = Arrays.asList(1,2,3,4,4,5,6);
        for(Integer i:list3){
            System.out.println(i);
        }
//        list.forEach();
        //使用lambda表达式
        list.forEach(i-> System.out.println(i));
        //使用方法体
        list.forEach(System.out::println);
    }

    public static <E> void printItem(E value, int index) {
        String output = String.format("%d -> %s", index, value);
        System.out.println(output);
    }

    public static interface ItemWithIndexVisitor<E> {
        public void visit(E item, int index);
    }

    public static <E> void eachWithIndex(List<E> list, ItemWithIndexVisitor<E> visitor) {
        for (int i = 0; i < list.size(); i++) {
            visitor.visit(list.get(i), i);
        }
    }


    public Supply getLambdaClosource() {
        int i = 1;
        return () -> {
            return i;
        };
    }

    public Supply getLambdaClosource2() {
        int i = 1;
        Supply<Integer> s = new Supply<Integer>() {
            @Override
            public Integer get() {
                return i;
            }
        };
        return s;
    }


    private interface Supply<T> {
        T get();
    }


    public static void loopTest() {
        List<Integer> list_1 = Arrays.asList(1, 2, 3, 3, 5, 5, 6, 7, 5);
        List<Integer> list_2 = Arrays.asList(1, 2, 3, 3, 5, 5, 6, 7, 5, 3, 3);
        list_1.forEach(one -> {
            list_2.forEach(two -> {
                if (one > two) {
                    System.out.println("one:" + one);
                }});});
        }





    }

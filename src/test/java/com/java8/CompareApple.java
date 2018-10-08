package com.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class CompareApple {


    public static void main(String[] args) {
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();

        List<Apple> list = Arrays.asList(apple1,apple2);
        list.sort(new AppleComparator2());
        //sort方法传入一个 Comparator
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //使用lambda表达式替代匿名内部类，使用了return
        list.sort((Apple a1,Apple a2)->{return a1.getWeight().compareTo(a2.getWeight());});
        //lambda表达式，省略了return
        list.sort((Apple a1,Apple a2)-> a1.getWeight().compareTo(a2.getWeight()));
        //lambda表达式，省略了类名
        list.sort((a1,a2)->a1.getWeight().compareTo(a2.getWeight()));
        //使用Comaprator的静态方法，静态方法传入了一个Function表达式
        list.sort(Comparator.comparing((a)->a.getWeight()));

        BinaryOperator b;
    }



    private static class AppleComparator2 implements Comparator<Apple>{

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight();
        }
    }

    private static class AppleComparator implements  Comparable<Apple>{

        @Override
        public int compareTo(Apple o) {
            return o.getWeight() ;
        }
    }



    private static class Apple implements Comparable{
        private String name;
        private Integer weight;

        @Override
        public int compareTo(Object o) {
            if(o instanceof  Apple){
                Apple apple = (Apple) o;
                return ((Apple) o).getWeight() - this.getWeight();
            }
            return 0;
        }
        /*@Override
        public int comparable(Object obj){

        }*/

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }


    }
}

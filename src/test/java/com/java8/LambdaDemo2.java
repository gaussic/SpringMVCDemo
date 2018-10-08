package com.java8;

import org.springframework.format.datetime.DateFormatter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo2 {
    public static void main(String[] args) {
        String name = "abc";

        Runnable r = () -> {
            System.out.println(name);
        };
//        name = "34";

        Map<String, Object> map = new HashMap<>();

//        BigDecimal b = (a) ->new BigDecimal(a);
//        BigDecimal b =


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 6);

        List<Integer> list2 = list.stream().filter(item -> {
            System.out.println(item);
            return item > 2;}).collect(Collectors.toList());


        list.stream().filter(item->item>2);
        System.out.println(list);
        System.out.println(list2);
    }

    public final static ThreadLocal<javax.swing.text.DateFormatter> formatter = ThreadLocal.withInitial(() -> {
        return new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MMM-yyyy"));
    });

}

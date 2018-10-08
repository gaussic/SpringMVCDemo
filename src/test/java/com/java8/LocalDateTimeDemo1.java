package com.java8;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeDemo1 {
    public static void main(String[] args) {
        Instant instant = Instant.now();

        Date date0 = Date.from(instant);

        System.out.println("instant" + instant + "," +instant.getEpochSecond());

        Date date = new Date();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        java.sql.Time time = new Time(date.getTime());

        Instant instant1 = date.toInstant();
        Instant instant2 = timestamp.toInstant();
//        Instant instant3 = time.toInstant();

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime1 = localDate.atTime(LocalTime.MIN);
        System.out.println("date:" + date + " , " + date.getTime());
        System.out.println(LocalTime.MIN);

        //instant 转换为 LocalDateTime
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime2);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("");
    }
}

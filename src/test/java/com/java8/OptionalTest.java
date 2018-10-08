package com.java8;

import com.gaussic.controller.CoalPipingController;
import com.sun.javafx.binding.StringFormatter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class OptionalTest {

    public static void main(String[] args) {
        Date beginTime = null;
        Date endTime = null;


        endTime = new Date(new Date().getTime() - 5* 60 * 60 * 1000);
        System.out.println(beginTime + ", " + endTime);
//        if(null!=beginTime && )
        Calendar beginC = new GregorianCalendar();
        beginC.setTimeZone(TimeZone.getDefault());
        Calendar endC = new GregorianCalendar();
        endC.setTimeZone(TimeZone.getDefault());


        BE be = new BE();

        beginC.set(Calendar.MINUTE, beginC.get(Calendar.MINUTE) - (int) (Math.random() * 30));

        LocalDateTime localDateTimeForBegin = LocalDateTime.now();
        LocalDateTime localDateTimeForEnd = LocalDateTime.now();

        Optional<Date> beginDateOpt = Optional.ofNullable(beginTime);
        Optional<Date> endDateOpt = Optional.ofNullable(endTime);
//        beginDateOpt.ifPresent(endDateOpt.ifPresent());
//        beginDateOpt.ifPresent(()->endDateOpt.ifPresent(System.out::println));
//
//        beginDateOpt.map(()->beginC.setTime(beginDateOpt.get());
//        );

        beginDateOpt.map(new Function<Date, Object>() {
            @Override
            public Object apply(Date date) {
                return null;
            }
        });
//        beginDateOpt.ifPresent(beginC::setTime).orElseGet();
        beginDateOpt.orElseGet(()->new Date());
        beginDateOpt.map((date) -> date).orElseGet(new Supplier<Date>() {
            @Override
            public Date get() {
                return null;
            }
        });
        beginDateOpt.orElseGet(()->{
            Date endDate = endDateOpt.orElseGet(()->{
                Date date = new Date();
                return date;
            });
            Date date = new Date(endDate.getTime() - 60 * 60* 1000);
            return date;
        });

        StringFormatter sf = new StringFormatter() {
            @Override
            protected String computeValue() {
                return null;
            }
        };

        endTime = new Date(new Date().getTime() - 3 * 60 * 60 * 1000);


        Date newEndTime = new Date();
        Date newBeginTime = beginDateOpt.orElseGet(()->{
            Date endDate = endDateOpt.orElseGet(Date::new);
            newEndTime.setTime(endDate.getTime());
            return new Date(endDate.getTime() - 60 * 60 * 1000);
        });

        System.out.println("开始时间：" + beginTime + ",            " + newBeginTime);
        System.out.println("结束时间：" + endTime + ",                  endDateOpt:" + endDateOpt.get() + ",         " +
                newEndTime);

        beginDateOpt.ifPresent((b) ->
                endDateOpt.ifPresent((e) ->
                        be.setBE(b, e)
                ));
        beginDateOpt.map(new Function<Date, Object>() {
            @Override
            public Object apply(Date date) {
                System.out.println("map:" + date);
                return null;
            }
        });
        beginDateOpt.ifPresent(new Consumer<Date>() {
            @Override
            public void accept(Date date) {
//                localDateTimeForBegin.
            }
        });
        Date newDate = beginDateOpt.orElseGet(new Supplier<Date>() {
            @Override
            public Date get() {
                return null;
            }
        });

        Optional<Object> oDate = beginDateOpt.map(new Function<Date, Object>() {
            @Override
            public Object apply(Date date) {
                return null;
            }
        });

        Optional<LocalDateTime> t = beginDateOpt.flatMap(new Function<Date, Optional<LocalDateTime>>() {
            @Override
            public Optional<LocalDateTime> apply(Date date) {
                return Optional.empty();
            }
        });
    }


    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        LocalDateTime localDateTime = null;
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    private static class BE {
        private LocalDateTime begin;
        private LocalDateTime end;

        public LocalDateTime getBegin() {
            return begin;
        }

        public void setBegin(LocalDateTime begin) {
            this.begin = begin;
        }

        public LocalDateTime getEnd() {
            return end;
        }

        public void setEnd(LocalDateTime end) {
            this.end = end;
        }

        public void setBE(Date b, Date e) {
            this.setBegin(getLocalDateTimeFromDate(b));
            this.setEnd(getLocalDateTimeFromDate(e));
        }
    }
}

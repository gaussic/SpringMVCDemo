package com.gaussic.util;

import com.gaussic.model.history.AcoalPipingHistoryEntity;
import com.gaussic.model.history.CoalPipingHistory;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReportHistoryUtil<T extends  CoalPipingHistory> {

    public   List<T> handleDataToReport(List<T> list, T coalPipingHistoryClass){
//        CoalPipingHistory coalPipingHistory = CoalPipingHistory.GeneratorCoalPipingHistory(coalPipingHistoryClass);
        List<T> list1 = new ArrayList<>();
        final List<T> reduce = list.stream().reduce(list1, (acoalPipingHistoryEntities, acoalPipingHistoryEntity1) -> {
            acoalPipingHistoryEntities.add(acoalPipingHistoryEntity1);
            return acoalPipingHistoryEntities;
        }, new BinaryOperator<List<T>>() {
            @Override
            public List<T> apply(List<T> acoalPipingHistoryEntities, List<T>
                    acoalPipingHistoryEntities2) {
                return null;
            }
        });


        ZoneId zoneId = ZoneId.systemDefault();
        //TODO 当第一个返回为null，进行处理
        //todo 当第一个的时间不是查询的开始时间，进行处理
        CoalPipingHistory acoalPipingHistoryEntityFirst = list.stream().findFirst().get();

        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instantNow = Instant.now();
        List<T> listResult = new ArrayList<>();
        List<T> listTemp = new ArrayList<>();

        System.out.println("数据size：" + list.size());
/*

        List<AcoalPipingHistoryEntity> list = acoalPipingHistoryEntityList.stream().reduce(listResult,(listTarget,a)->{
            Instant instantCurrent = Instant.ofEpochMilli(a.gethTime().getTime());
            Duration duration = Duration.between(instantCurrent,instantNow);
            listTemp.add(a);
            long durationSeconds = duration.getSeconds();
            System.out.println("durationSeconds: " + durationSeconds);
            if(durationSeconds%(300 + listTemp.size() * 300)>= 2){
                //TODO 当数据存在，数据值为null时，进行判断
                double densityAAvg = listTemp.stream().mapToDouble((aa)->{
//                    System.out.println("-------------------------" +aa + "," + aa.gethPipeADencity());
                    Float density = aa.gethPipeADencity();
                    Optional<Float> optionalFloat = Optional.ofNullable(density);
                    Optional<Double> optionalDouble = Optional.of(new Double(optionalFloat.orElseGet(()->new Float
                            (0))));
                    ;
                    return optionalDouble.get();}).average().getAsDouble();
                listTemp.clear();
                AcoalPipingHistoryEntity acoalPipingHistoryEntity1 = new AcoalPipingHistoryEntity();
                acoalPipingHistoryEntity1.sethPipeADencity(new Double(densityAAvg).floatValue());
                listTarget.add(acoalPipingHistoryEntity1);
            }
           return listTarget;
        },(listParam,aParm)->listParam);
*/
        /*public static <T, K>Collector<T, ?, Map<K, List<T>>>
        groupingBy(Function<? super T, ? extends K> classifier) {
            return groupingBy(classifier, toList());
        }*/
        Function<T,String> groupByingFunction =(a) -> {
            Instant instantCurrent = Instant.ofEpochMilli(a.gethTime().getTime());
            Duration duration = Duration.between(instantCurrent,instantNow);
            listTemp.add(a);
            long durationSeconds = duration.getSeconds();
            int result = (int)Math.floor(durationSeconds/300);
            return String.valueOf(result);
        };
        Map<String, List<T>> map = list.stream().collect(Collectors
                .groupingBy(groupByingFunction));
        System.out.println("map.siez:" +map.size());
//        map.forEach(new BiConsumer<String, List<AcoalPipingHistoryEntity>>() {
//            @Override
//            public void accept(String s, List<AcoalPipingHistoryEntity> acoalPipingHistoryEntities) {
//
//            }
//        });
//        map.
//        map.forEach((s,list)->{
//            System.out.println(s + "," + list.size());
//        });
        List<Map.Entry<String,List<T>>> listA = new ArrayList<>(map.entrySet());
        Collections.sort(listA, Comparator.comparing((b)->Integer.parseInt(b.getKey())));
     /*   list.forEach((l)->{
            System.out.println(l.getKey());
        });*/

        List<T> list2 = new ArrayList<>();
        List<List<T>> list3 = listA.stream().map((l)->l.getValue()).collect(Collectors.toList());
        //list3对数据进行统计分析
        List<T> list5 = list3.stream().map((l2)->{
            //TODO 第一个数据可以能为null
            T acoalPipingHistoryEntity3 = l2.stream().findFirst().get();
            double densityAAvg = l2.stream().mapToDouble((aphe)->{
                if(null == aphe.gethPipeADencity())
                    return 0;
                return aphe.gethPipeADencity();}).average().orElse
                    (new Double(0));
            acoalPipingHistoryEntity3.sethPipeADencity((float) densityAAvg);
            return acoalPipingHistoryEntity3;
        }).collect(Collectors.toList());

        System.out.println("list5:" + list5.size() + "," + list5.stream().findFirst().get().gethPipeADencity());
        list5.forEach((l)->{
            System.out.println(l.gethTime() + "," + l.gethPipeADencity());
        });
        List<CoalPipingHistory> list4 = list3.stream().flatMap(l->l.stream()).collect(Collectors.toList());

        return list5;
    }
}

package com.java8;

import com.gaussic.model.history.AcoalPipingHistoryEntity;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class LambdaDemo3 {
    public static void main(String[] args) {
        List<AcoalPipingHistoryEntity> acoalPipingHistoryEntityList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        for(int i=0;i<10;i++){
            AcoalPipingHistoryEntity acoalPipingHistoryEntity = new AcoalPipingHistoryEntity();
            acoalPipingHistoryEntity.sethPipeADencity(new Float(i));
            LocalDateTime localDateTimeTemp = localDateTime.plusMinutes(5*i);
            acoalPipingHistoryEntity.sethTime(Timestamp.valueOf(localDateTimeTemp));
            acoalPipingHistoryEntityList.add(acoalPipingHistoryEntity);
        }

        List<AcoalPipingHistoryEntity> list1 = new ArrayList<>();
        List<AcoalPipingHistoryEntity> list3 = new ArrayList<>();

        List<AcoalPipingHistoryEntity> list4 = acoalPipingHistoryEntityList.stream().reduce(list1, (list2, a) -> {
                    list3.add(a);
//                    System.out.println("list2: init size: " + list2.size());
                    //进行localTime的比较
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDateTime localDateTime1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(a.gethTime().getTime()),
                            zoneId);

                    Instant instantStart = localDateTime.atZone(zoneId).toInstant();
                    Instant instantCurrent = localDateTime1.atZone(zoneId).toInstant();
                    Duration duration = Duration.between(instantStart,instantCurrent);
                    long durationSeconds = duration.getSeconds();
                    if(list2.size() == 0){
                        list2.add(a);
                        System.out.println("list 初始化：");
                    }
                    System.out.println("durationSeconds: " + durationSeconds);
                    if(durationSeconds >= 600 * list2.size()){
                        AcoalPipingHistoryEntity aTemp = new AcoalPipingHistoryEntity();
                        aTemp.sethTime(list3.stream().findFirst().get().gethTime());
                        Double sum = list3.stream().mapToDouble((i)->i.gethPipeADencity()).sum();
                        aTemp.sethPipeADencity(sum.floatValue());
                        System.out.println("添加" + list3.size());
                        list2.add(aTemp);
                        list3.clear();
                    }

//                    System.out.println("list2:" + a.gethTime());
                    return list2;
                },(list5,df)->list5);
        System.out.println("list4:"+ list4.size());
        list4.stream().forEach((a)->System.out.println(a.gethPipeADencity()));
    }
}

package com.gaussic.controller;

import com.gaussic.model.history.*;
import com.gaussic.repository.CoalPipingHistoryRepositoryA;
import com.gaussic.repository.CoalPipingHistoryRepositoryB;
import com.gaussic.repository.CoalPipingHistoryRepositoryC;
import com.gaussic.repository.CoalPipingHistoryRepositoryD;
import com.gaussic.service.CoalPipingHistoryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class HistoryReportController {
    @Autowired
    private CoalPipingHistoryService<AcoalPipingHistoryEntity> coalPipingHistoryServiceA;
    @Autowired
    private CoalPipingHistoryService<BcoalPipingHistoryEntity> coalPipingHistoryServiceB;
    @Autowired
    private CoalPipingHistoryService<CcoalPipingHistoryEntity> coalPipingHistoryServiceC;
    @Autowired
    private CoalPipingHistoryService<DcoalPipingHistoryEntity> coalPipingHistoryServiceD;
    @Autowired
    private CoalPipingHistoryRepositoryA coalPipingHistoryRepositoryA;
    @Autowired
    private CoalPipingHistoryRepositoryB coalPipingHistoryRepositoryB;
    @Autowired
    private CoalPipingHistoryRepositoryC coalPipingHistoryRepositoryC;
    @Autowired
    private CoalPipingHistoryRepositoryD coalPipingHistoryRepositoryD;





    @RequestMapping(value = "/queryHistoryDataForReport", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String queryHistoryDataForReport(@RequestParam(value = "queryDate", required = false) @DateTimeFormat(pattern =
            "yyyy-MM-dd") Date queryDate) {
        Optional<Date> optionalDate = Optional.ofNullable(queryDate);
        Date date = optionalDate.orElseGet(Date::new);
//        Optional<LocalDate> queryDateOptional = Optional.ofNullable(queryDate);
//        LocalDate queryTargetDate = queryDateOptional.orElseGet(LocalDate::now);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate queryTargetDate = instant.atZone(zoneId).toLocalDate();

        Timestamp beginTimestamp = Timestamp.valueOf(LocalDateTime.of(queryTargetDate, LocalTime.MIN));
        Timestamp endTimestamp = Timestamp.valueOf(LocalDateTime.of(queryTargetDate, LocalTime.MAX));
        List<AcoalPipingHistoryEntity> acoalPipingHistoryEntityList = coalPipingHistoryRepositoryA
                .findByHTimeBetween(beginTimestamp, endTimestamp);
        List<BcoalPipingHistoryEntity> bcoalPipingHistoryEntityList = coalPipingHistoryRepositoryB.findByHTimeBetween
                (beginTimestamp, endTimestamp);
        List<CcoalPipingHistoryEntity> ccoalPipingHistoryEntityList = coalPipingHistoryRepositoryC.findByHTimeBetween
                (beginTimestamp, endTimestamp);
        List<DcoalPipingHistoryEntity> dcoalPipingHistoryEntityList = coalPipingHistoryRepositoryD.findByHTimeBetween
                (beginTimestamp, endTimestamp);

//        acoalPipingHistoryEntityList.stream().collect(Collectors.groupingBy)
//        <U> U reduce(U identity,
//                BiFunction<U, ? super T, U> accumulator,
//                BinaryOperator<U> combiner);

        List<AcoalPipingHistoryEntity> coalPipingHistoryForAList = coalPipingHistoryServiceA.handleDataToReport
                (acoalPipingHistoryEntityList);
        List<BcoalPipingHistoryEntity> coalPipingHistoryForBList = coalPipingHistoryServiceB.handleDataToReport
                (bcoalPipingHistoryEntityList);
        List<CcoalPipingHistoryEntity> coalPipingHistoryForCList = coalPipingHistoryServiceC.handleDataToReport
                (ccoalPipingHistoryEntityList);
        List<DcoalPipingHistoryEntity> coalPipingHistoryForDList = coalPipingHistoryServiceD.handleDataToReport
                (dcoalPipingHistoryEntityList);


        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArrayForMillA = getJsonArray(coalPipingHistoryForAList);
        JSONArray jsonArrayForMillB = getJsonArray(coalPipingHistoryForBList);
        JSONArray jsonArrayForMillC = getJsonArray(coalPipingHistoryForCList);
        JSONArray jsonArrayForMillD = getJsonArray(coalPipingHistoryForDList);


        jsonObject.put("millA",jsonArrayForMillA);
        jsonObject.put("millB",jsonArrayForMillB);
        jsonObject.put("millC",jsonArrayForMillC);
        jsonObject.put("millD",jsonArrayForMillD);


        return jsonObject.toString();
    }

    private JSONArray getJsonArray(List<? extends CoalPipingHistory> list) {
        JSONArray jsonArray = new JSONArray();
//        CoalPipingHistory coalPipingHistory = new AcoalPipingHistoryEntity();
        Comparator<CoalPipingHistory> comparator = Comparator.comparing((l)->l.gethTime());
        list.sort(comparator);
        for(int i=0;i<list.size();i++){
            JSONObject jsonObjData = new JSONObject();
            CoalPipingHistory coalPipingHistory = list.get(i);
            String timeStr = coalPipingHistory.gethTime().toLocalDateTime().toLocalTime().format(DateTimeFormatter
                    .ofPattern("HH:mm"));
//            System.out.println(timeStr);
            jsonObjData.put("t",timeStr);
            jsonObjData.put("AD",coalPipingHistory.gethPipeADencityWithFormat(0));
            jsonObjData.put("BD",coalPipingHistory.gethPipeBDencityWithFormat(0));
            jsonObjData.put("CD",coalPipingHistory.gethPipeCDencityWithFormat(0));
            jsonObjData.put("DD",coalPipingHistory.gethPipeDDencityWithFormat(0));

            jsonObjData.put("AV",coalPipingHistory.gethPipeAVelocityWithFormat(0));
            jsonObjData.put("BV",coalPipingHistory.gethPipeBVelocityWithFormat(0));
            jsonObjData.put("CV",coalPipingHistory.gethPipeCVelocityWithFormat(0));
            jsonObjData.put("DV",coalPipingHistory.gethPipeDVelocityWithFormat(0));
            jsonArray.put(jsonObjData);
        }
        return jsonArray;
    }


    public void  test(){
        /*AcoalPipingHistoryEntity acoalPipingHistoryEntity = new AcoalPipingHistoryEntity();
        List<AcoalPipingHistoryEntity> list1 = new ArrayList<>();
        final List<AcoalPipingHistoryEntity> reduce = acoalPipingHistoryEntityList.stream().reduce(list1, (acoalPipingHistoryEntities, acoalPipingHistoryEntity1) -> {
            acoalPipingHistoryEntities.add(acoalPipingHistoryEntity1);
            return acoalPipingHistoryEntities;
        }, new BinaryOperator<List<AcoalPipingHistoryEntity>>() {
            @Override
            public List<AcoalPipingHistoryEntity> apply(List<AcoalPipingHistoryEntity> acoalPipingHistoryEntities, List<AcoalPipingHistoryEntity> acoalPipingHistoryEntities2) {
                return null;
            }
        });


        ZoneId zoneId = ZoneId.systemDefault();
        //TODO 当第一个返回为null，进行处理
        //todo 当第一个的时间不是查询的开始时间，进行处理
        AcoalPipingHistoryEntity acoalPipingHistoryEntityFirst = acoalPipingHistoryEntityList.stream().findFirst().get();

        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instantNow = Instant.now();
        List<AcoalPipingHistoryEntity> listResult = new ArrayList<>();
        List<AcoalPipingHistoryEntity> listTemp = new ArrayList<>();

        System.out.println("数据size：" + acoalPipingHistoryEntityList.size());
*//*

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
*//*
        *//*public static <T, K>Collector<T, ?, Map<K, List<T>>>
        groupingBy(Function<? super T, ? extends K> classifier) {
            return groupingBy(classifier, toList());
        }*//*
        Function<AcoalPipingHistoryEntity,String> groupByingFunction =(a) -> {
            Instant instantCurrent = Instant.ofEpochMilli(a.gethTime().getTime());
            Duration duration = Duration.between(instantCurrent,instantNow);
            listTemp.add(a);
            long durationSeconds = duration.getSeconds();
            int result = (int)Math.floor(durationSeconds/300);
            return String.valueOf(result);
        };
        Map<String, List<AcoalPipingHistoryEntity>> map = acoalPipingHistoryEntityList.stream().collect(Collectors
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
        List<Map.Entry<String,List<AcoalPipingHistoryEntity>>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,Comparator.comparing((b)->Integer.parseInt(b.getKey())));
     *//*   list.forEach((l)->{
            System.out.println(l.getKey());
        });*//*

        List<AcoalPipingHistoryEntity> list2 = new ArrayList<>();
        List<List<AcoalPipingHistoryEntity>> list3 = list.stream().map(Map.Entry::getValue).collect(Collectors.toList());
        //list3对数据进行统计分析
        List<AcoalPipingHistoryEntity> list5 = list3.stream().map((l2)->{
            //TODO 第一个数据可以能为null
            AcoalPipingHistoryEntity acoalPipingHistoryEntity3 = l2.stream().findFirst().get();
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
        List<AcoalPipingHistoryEntity> list4 = list3.stream().flatMap(l->l.stream()).collect(Collectors.toList());
//        Collections.sor
//        System.out.println("listResult.size:" + list.size());

        *//*List<AcoalPipingHistoryEntity> list4 = acoalPipingHistoryEntityList.stream().reduce(list1, (list2, a) -> {
                    list2.add(a);
//                    System.out.println("list2" + a);
                    return list2;
                },
                (list3, a2) -> list3);

        acoalPipingHistoryEntityList.stream().reduce(acoalPipingHistoryEntity, new BinaryOperator<AcoalPipingHistoryEntity>() {
            @Override
            public AcoalPipingHistoryEntity apply(AcoalPipingHistoryEntity acoalPipingHistoryEntity, AcoalPipingHistoryEntity acoalPipingHistoryEntity2) {
                return null;
            }
        });*/
    }
}

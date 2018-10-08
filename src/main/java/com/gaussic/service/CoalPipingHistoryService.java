package com.gaussic.service;

import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.history.*;
import com.gaussic.repository.CoalPipingHistoryRepositoryA;
import com.gaussic.repository.CoalPipingHistoryRepositoryB;
import com.gaussic.repository.CoalPipingHistoryRepositoryC;
import com.gaussic.repository.CoalPipingHistoryRepositoryD;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CoalPipingHistoryService<T extends CoalPipingHistory> {
    @Autowired
    private CoalPipingHistoryRepositoryA coalPipingHistoryRepositoryA;
    @Autowired
    private CoalPipingHistoryRepositoryB coalPipingHistoryRepositoryB;
    @Autowired
    private CoalPipingHistoryRepositoryC coalPipingHistoryRepositoryC;
    @Autowired
    private CoalPipingHistoryRepositoryD coalPipingHistoryRepositoryD;


//    public String generateJsonStringByHistroyList(List<AcoalPipingHistoryEntity> coalPipeHistoryEntityList, Calendar beginC,
//                                                  Calendar endC){
//        List<CoP>
//    }
    public String generateJsonStringByHistroyList(List<T> coalPipeHistoryEntityList,
                                                  Timestamp beginT,
                                                  Timestamp endT) {
        Calendar beginC = new GregorianCalendar();
        beginC.setTimeInMillis(beginT.getTime());
        Calendar endC = new GregorianCalendar();
        endC.setTimeInMillis(endT.getTime());
        return generateJsonStringByHistroyList(coalPipeHistoryEntityList,beginC,endC);

    }

    public String generateJsonStringByHistroyList(List<T> coalPipeHistoryEntityList,
                                                  Calendar beginC,
                                                  Calendar endC) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArrayForPipe1Density = new JSONArray();
        JSONArray jsonArrayForPipe2Density = new JSONArray();
        JSONArray jsonArrayForPipe3Density = new JSONArray();
        JSONArray jsonArrayForPipe4Density = new JSONArray();
        JSONArray jsonArrayForPipe1Velocity = new JSONArray();
        JSONArray jsonArrayForPipe2Velocity = new JSONArray();
        JSONArray jsonArrayForPipe3Velocity = new JSONArray();
        JSONArray jsonArrayForPipe4Velocity = new JSONArray();
        if (null != coalPipeHistoryEntityList) {
            for (CoalPipingHistory h : coalPipeHistoryEntityList) {
                jsonArrayForPipe1Density.put(h.gethPipeADencity());
                jsonArrayForPipe2Density.put(h.gethPipeBDencity());
                jsonArrayForPipe3Density.put(h.gethPipeCDencity());
                jsonArrayForPipe4Density.put(h.gethPipeDDencity());

                jsonArrayForPipe1Velocity.put(h.gethPipeAVelocity());
                jsonArrayForPipe2Velocity.put(h.gethPipeBVelocity());
                jsonArrayForPipe3Velocity.put(h.gethPipeCVelocity());
                jsonArrayForPipe4Velocity.put(h.gethPipeDVelocity());
            }
        }

        jsonObject.put("startTime", beginC.getTimeInMillis());
        jsonObject.put("endTime", endC.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        jsonObject.put("startTimeStr", simpleDateFormat.format(beginC.getTime()));
        jsonObject.put("endTimeStr", simpleDateFormat.format(endC.getTime()));

        jsonObject.put("pipe1Density", jsonArrayForPipe1Density);
        jsonObject.put("pipe2Density", jsonArrayForPipe2Density);
        jsonObject.put("pipe3Density", jsonArrayForPipe3Density);
        jsonObject.put("pipe4Density", jsonArrayForPipe4Density);

        jsonObject.put("pipe1Velocity", jsonArrayForPipe1Velocity);
        jsonObject.put("pipe2Velocity", jsonArrayForPipe2Velocity);
        jsonObject.put("pipe3Velocity", jsonArrayForPipe3Velocity);
        jsonObject.put("pipe4Velocity", jsonArrayForPipe4Velocity);


        return jsonObject.toString();
    }

    /**
     * 生成一个 history对象
     *
     * @param coalMillEntity
     * @param now
     * @return
     */
    public CoalPipingHistory generatorHistory(CoalMillEntity coalMillEntity, Date now) {
        Long coalMillId = coalMillEntity.getId();
        CoalPipingHistory coalPipingHistory = null;
        switch (coalMillId.intValue()) {
            case 1:
                coalPipingHistory = new AcoalPipingHistoryEntity(coalMillEntity, now);
                coalPipingHistoryRepositoryA.save((AcoalPipingHistoryEntity) coalPipingHistory);
                break;
            case 2:
                coalPipingHistory = new BcoalPipingHistoryEntity(coalMillEntity, now);
                coalPipingHistoryRepositoryB.save((BcoalPipingHistoryEntity) coalPipingHistory);
                break;
            case 3:
                coalPipingHistory = new CcoalPipingHistoryEntity(coalMillEntity, now);
                coalPipingHistoryRepositoryC.save((CcoalPipingHistoryEntity) coalPipingHistory);
                break;
            case 4:
                coalPipingHistory = new DcoalPipingHistoryEntity(coalMillEntity, now);
                coalPipingHistoryRepositoryD.save((DcoalPipingHistoryEntity) coalPipingHistory);
                break;
            default:
                break;
        }
        return coalPipingHistory;
    }

    private CoalPipingHistory setCoalPipingHistory(CoalPipingEntity coalPipingEntity, CoalPipingHistory coalPipingHistoryEntity) {
        String pipingLocation = coalPipingEntity.getpLocation();
        Float velocity = coalPipingEntity.getpVelocity();
        Float density = coalPipingEntity.getpDencity();
        switch (pipingLocation) {
            case "A":
                coalPipingHistoryEntity.sethPipeADencity(density);
                coalPipingHistoryEntity.sethPipeAVelocity(velocity);
                break;
            case "B":
                coalPipingHistoryEntity.sethPipeBDencity(density);
                coalPipingHistoryEntity.sethPipeBVelocity(velocity);
                break;
            case "C":
                coalPipingHistoryEntity.sethPipeCDencity(density);
                coalPipingHistoryEntity.sethPipeCVelocity(velocity);
                break;
            case "D":
                coalPipingHistoryEntity.sethPipeDDencity(density);
                coalPipingHistoryEntity.sethPipeDVelocity(velocity);
                break;
            default:
                break;
        }


        return coalPipingHistoryEntity;
    }

    /**
     * 更新历史数据
     *
     * @param coalMillId
     * @param coalPipingHistoryEntity
     */
    public void updateCoalPipingHistory(Long coalMillId, CoalPipingHistory coalPipingHistoryEntity) {

        switch (coalMillId.intValue()) {
            case 1:
                coalPipingHistoryRepositoryA.saveAndFlush((AcoalPipingHistoryEntity) coalPipingHistoryEntity);
                break;
            case 2:
                coalPipingHistoryRepositoryB.saveAndFlush((BcoalPipingHistoryEntity) coalPipingHistoryEntity);
                break;
            case 3:
                coalPipingHistoryRepositoryC.saveAndFlush((CcoalPipingHistoryEntity) coalPipingHistoryEntity);
                break;
            case 4:
                coalPipingHistoryRepositoryD.saveAndFlush((DcoalPipingHistoryEntity) coalPipingHistoryEntity);
                break;
            default:
                break;
        }
    }

    /**
     * 更新历史数据
     *
     * @param coalPipingEntityList    所有的对象归属于一个Mill
     * @param coalPipingHistoryEntity
     */
    public void updateHistory(List<CoalPipingEntity> coalPipingEntityList, CoalPipingHistory coalPipingHistoryEntity) {
        for (CoalPipingEntity coalPipingEntity : coalPipingEntityList) {
            setCoalPipingHistory(coalPipingEntity, coalPipingHistoryEntity);
        }
        updateCoalPipingHistory(coalPipingEntityList.get(0).getpCoalMillId(), coalPipingHistoryEntity);
    }

    /**
     * 根据coalPiping的实时数据，更新历史数据
     *
     * @param coalPipingEntity
     * @param coalPipingHistoryEntity
     */
    public void updateHistory(CoalPipingEntity coalPipingEntity, CoalPipingHistory coalPipingHistoryEntity) {
        //设置历史数据ABCD四根管的密度与风速
        setCoalPipingHistory(coalPipingEntity, coalPipingHistoryEntity);
        Long coalMillId = coalPipingEntity.getpCoalMillId();
        //保存粉管历史数据
//        updateCoalPipingHistory(coalMillId,coalPipingHistoryEntity);


    }


    public List<T> handleDataToReport(List<T> list) {
        //TODO 当第一个返回为null，进行处理
        //todo 当第一个的时间不是查询的开始时间，进行处理
//        Instant.
        Instant instantNow = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.now();
//        instantNow = localDateTime.toInstant(ZoneOffset.of());

        LocalDateTime localDateTime1 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        Instant instantNow2 = localDateTime1.toInstant(ZoneOffset.of("+00:00"));

//        System.out.println("数据size：" + list.size());
        //01-数据分组函数，分组条件为每300秒分一组
        Function<T, String> groupByingFunction = (a) -> {
            long durationsSeconds = getDurationBetweenNowAndCurrent(instantNow2, a);
            int result = (int) Math.floor(durationsSeconds / 300);
//            System.out.println("分组result：" + result);
            return String.valueOf(result);
        };
        //数据分组，返回map格式的数据
        Map<String, List<T>> map = list.stream().collect(Collectors.groupingBy(groupByingFunction));

        //02-将map对象转换为list<map.Entry>
        List<Map.Entry<String, List<T>>> listA = new ArrayList<>(map.entrySet());
        //03-对分组后的数据进行排序，默认为降序，使用comparator.reversed方法进行反转，实现升序排列。
        Comparator<Map.Entry<String, List<T>>> comparator = Comparator.comparing((b) -> Integer.parseInt(b.getKey()));
//        Collections.sort(listA, comparator.reversed());
        listA.sort(comparator.reversed());
        listA.forEach((l) -> {
//            System.out.println(l.getKey() + "," + l.getValue().size() + ",  min:  " + l.getValue().stream().min(Comparator.comparing(T::gethTime)).get().gethTime());
        });


        //04-将List<Map.entry>转换为List<List<T>>对象，使用map方法
        List<List<T>> list3 = listA.stream().map((l) -> l.getValue()).collect(Collectors.toList());
       /* for(int i=0;i<list3.size();i++){
            List<T> listT = list3.get(i);
            T t = list.stream().findFirst().get();
            LocalDateTime localDateTime = t.gethTime().toLocalDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            LocalDateTime localDateTime1 = LocalDateTime.of(localDate,null);
            localDateTime1.plusMinutes(i * 5);
            t.sethTime(Timestamp.valueOf(localDateTime1));
        }*/
        //05-对List<List<T>>数据进行统计分析,生成List<T>集合
        List<T> list5 = list3.stream().map((l2) -> {
            //TODO 第一个数据可以能为null
            //TODO new一个 T 对象，时间设置为00:00 开始，下一个对象为5分钟以后的对象
//            T t = CoalPipingHistory.GeneratorCoalPipingHistory();
//            T t = l2.stream().findFirst().get();
            T t = l2.stream().min(Comparator.comparing(T::gethTime)).get();
            //TODO 对数据进行平均取值，此方法需要设置接口，
            //TODO 当数据为null时，返回了0，平均的时候，将0计入，并进行了平均【这样的方式存在问题】
            //TODO              如果数据为null，返回0，数据平均的时候，不应该计入平均数中。
            /*double densityAAvg = l2.stream().mapToDouble((ache) ->
                    new Double(Optional.ofNullable(ache.gethPipeAVelocity()).orElse(0f)))
                    .average().orElse(0d);*/
            double densityAAvg = l2.stream().mapToDouble(T::getPipeADensityNotNull).average().orElse(0d);
            double densityBAvg = l2.stream().mapToDouble(T::getPipeBDensityNotNull).average().orElse(0d);
            double densityCAvg = l2.stream().mapToDouble(T::getPipeCDensityNotNull).average().orElse(0d);
            double densityDAvg = l2.stream().mapToDouble(T::getPipeDDensityNotNull).average().orElse(0d);
            double velocityAAvg = l2.stream().mapToDouble(T::getPipeAVelocityNotNull).average().orElse(0d);
            double velocityBAvg = l2.stream().mapToDouble(T::getPipeBVelocityNotNull).average().orElse(0d);
            double velocityCAvg = l2.stream().mapToDouble(T::getPipeCVelocityNotNull).average().orElse(0d);
            double velocityDAvg = l2.stream().mapToDouble(T::getPipeDVelocityNotNull).average().orElse(0d);

            t.sethPipeADencity((float) densityAAvg);
            t.sethPipeBDencity((float) densityBAvg);
            t.sethPipeCDencity((float) densityCAvg);
            t.sethPipeDDencity((float) densityDAvg);

            t.sethPipeAVelocity((float) velocityAAvg);
            t.sethPipeBVelocity((float) velocityBAvg);
            t.sethPipeCVelocity((float) velocityCAvg);
            t.sethPipeDVelocity((float) velocityDAvg);
//            double densityBAvg = l2.stream().map()
//            l2.stream().map((acphe)->)
            return t;
        }).collect(Collectors.toList());

        /* System.out.println("list5:" + list5.size() + "," + list5.stream().findFirst().get().gethPipeADencity());*/
        list5.forEach((l) -> {
//            System.out.println(l.gethTime() + "," + l.gethPipeADencity());
        });

        return list5;
    }

    private long getDurationBetweenNowAndCurrent(Instant instantNow, T a) {
//        Instant instantCurrent = Instant.ofEpochMilli(a.gethTime().getTime());
//        instantCurrent.atOffset(ZoneOffset.of("+08:00"));
        Instant instant = a.gethTime().toLocalDateTime().toInstant(ZoneOffset.of("+00:00"));
        Duration duration = Duration.between(instantNow, instant);
        return duration.getSeconds();
    }

}

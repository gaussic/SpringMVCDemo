package com.gaussic.controller;

import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.history.*;
import com.gaussic.repository.*;
import com.gaussic.service.CoalPipingHistoryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Controller
public class CoalPipingController {
    @Autowired
    private CoalPipingRepository coalPipingRepository;
    @Autowired
    private CoalPipingHistoryRepositoryA coalPipingHistoryRepositoryA;
    @Autowired
    private CoalPipingHistoryRepositoryB coalPipingHistoryRepositoryB;
    @Autowired
    private CoalPipingHistoryRepositoryC coalPipingHistoryRepositoryC;
    @Autowired
    private CoalPipingHistoryRepositoryD coalPipingHistoryRepositoryD;
    @Autowired
    private CoalPipingHistoryService coalPipingHistoryService;

    /**
     * 获取A磨当前浓度实时数据，格式为{time:1111,data[ADensity,B,C,D]}
     *
     * @return
     */
    @RequestMapping(value = "/getRealTimeData", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getRealTimeData(@RequestParam(value = "latestTime", required = false) Long latestTime) {
        //TODO 需要进行时间的判定，判断获取的数据是否是最新的数据，如果数据一致不更新，则产生新的时间，数据为0的数据。浓度数据为0，
        //TODO 相对数据则需要重新规划定义
        //TODO 通过hibernate数据分组功能，将数据分组，使用视图的方式，将数据形成新的实体，就不需要对数据进行单独处理了。
        //TODO 数据采集与处理的时间达到了1.9秒，需要处理
        if (null != latestTime) {
            System.out.println("latestTime:" + latestTime);
        }

        CoalPipingEntity coalPipingEntityForA = coalPipingRepository.findOne(11L);
        CoalPipingEntity coalPipingEntityForB = coalPipingRepository.findOne(12L);
        CoalPipingEntity coalPipingEntityForC = coalPipingRepository.findOne(13L);
        CoalPipingEntity coalPipingEntityForD = coalPipingRepository.findOne(14L);

        long currentTime = getCurrentTime(latestTime, coalPipingEntityForA.getpTime().getTime(), coalPipingEntityForB.getpTime().getTime(), coalPipingEntityForC.getpTime().getTime(), coalPipingEntityForD.getpTime().getTime());

        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("density",coalPipingEntityForA.getpDencity());
//        jsonObject.put("velocity",coalPipingEntityForA.getpVelocity());

        jsonObject.put("time", coalPipingEntityForA.getpTime().getTime());
        JSONArray tempArrayData = new JSONArray();
        tempArrayData.put(coalPipingEntityForA.getpDencity());
        tempArrayData.put(coalPipingEntityForB.getpDencity());
        tempArrayData.put(coalPipingEntityForC.getpDencity());
        tempArrayData.put(coalPipingEntityForD.getpDencity());
        jsonObject.put("data", tempArrayData);

        return jsonObject.toString();
    }


    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        LocalDateTime localDateTime = null;
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    private static class BE {
        private Timestamp beginTimestamp;
        private Timestamp endTimestamp;

        public BE(Date beginTime,Date endTime){
            System.out.println(beginTime + ", " + endTime);
//        if(null!=beginTime && )
            Calendar beginC = new GregorianCalendar();
            beginC.setTimeZone(TimeZone.getDefault());
            Calendar endC = new GregorianCalendar();
            endC.setTimeZone(TimeZone.getDefault());
            beginC.set(Calendar.MINUTE, beginC.get(Calendar.MINUTE) - (int) (Math.random() * 30));

            LocalDateTime localDateTimeForBegin = LocalDateTime.now();
            LocalDateTime localDateTimeForEnd = LocalDateTime.now();

            Optional<Date> beginDateOpt = Optional.ofNullable(beginTime);
            Optional<Date> endDateOpt = Optional.ofNullable(endTime);
//        beginDateOpt.ifPresent(endDateOpt.ifPresent());
//        beginDateOpt.ifPresent(()->endDateOpt.ifPresent(System.out::println));
            beginDateOpt.ifPresent((b) ->
                    endDateOpt.ifPresent((e) ->
                    {
//                        be.setBE(b, e)
                    }
                    ));
            beginDateOpt.map(new Function<Date, Object>() {
                @Override
                public Object apply(Date date) {
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


            if (null != beginTime && null != endTime) {
                beginC.setTime(beginTime);
                endC.setTime(endTime);
            } else if (null != beginTime && endTime == null) {
                beginC.setTime(beginTime);
                endC.setTime(beginTime);
                endC.set(Calendar.HOUR, beginC.get(Calendar.HOUR) + 1);
            } else if (null == beginTime && null != endTime) {
                endC.setTime(endTime);
                beginC.setTime(endTime);
                beginC.set(Calendar.HOUR, endC.get(Calendar.HOUR) - 1);
            } else if (null == beginTime && null == endTime) {
                endC.setTime(new Date());
                beginC.setTime(new Date());
                beginC.set(Calendar.HOUR, beginC.get(Calendar.HOUR) - 1);
            }

            beginTimestamp = new Timestamp(beginC.getTimeInMillis());
            endTimestamp = new Timestamp(endC.getTimeInMillis());
        }

        public Timestamp getBeginTimestamp() {
            return beginTimestamp;
        }

        public void setBeginTimestamp(Timestamp beginTimestamp) {
            this.beginTimestamp = beginTimestamp;
        }

        public Timestamp getEndTimestamp() {
            return endTimestamp;
        }

        public void setEndTimestamp(Timestamp endTimestamp) {
            this.endTimestamp = endTimestamp;
        }



    }

    /**
     * 查询历史曲线数据
     * @param beginTime
     * @param endTime
     * @param millLocation
     * @return
     */
    @RequestMapping(value = "/getMillHistoryDataWithMill", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMillHistoryDataWithMill(@RequestParam(value = "startInputTime", required = false) Date beginTime, @RequestParam(value = "endInputTime", required = false) Date endTime,@RequestParam(value="mill" ,required = false)String millLocation){
        //TODO 历史曲线数据，缺少磨煤机磨煤量的数据返回
        BE be = new BE(beginTime,endTime);
        List<? extends CoalPipingHistory> list = null;
        if(null != millLocation){
            if(millLocation.equals("A")){
                list = coalPipingHistoryRepositoryA.findByHTimeBetween(be.getBeginTimestamp(),be.getEndTimestamp());
            }else if(millLocation.equals("B")){
                list = coalPipingHistoryRepositoryB.findByHTimeBetween(be.getBeginTimestamp(),be.getEndTimestamp());
            }else if(millLocation.equals("C")){
                list = coalPipingHistoryRepositoryC.findByHTimeBetween(be.getBeginTimestamp(),be.getEndTimestamp());
            }else if(millLocation.equals("D")){
                list = coalPipingHistoryRepositoryD.findByHTimeBetween(be.getBeginTimestamp(),be.getEndTimestamp());
            }
        }
        //TODO 处理异常数据
        if(null == list || list.size() == 0){
            list = handleExceptionHistoryList(be);
        }
        return coalPipingHistoryService.generateJsonStringByHistroyList(list, be
                .getBeginTimestamp(), be.getEndTimestamp());
    }
    /**
     * 获取A磨的历史数据
     *
     * @return
     */
    @RequestMapping(value = "/getAMillHistoryData", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAMillHistoryData(@RequestParam(value = "startInputTime", required = false) Date beginTime, @RequestParam(value = "endInputTime", required = false) Date endTime) {

        BE be = new BE(beginTime,endTime);


        List<? extends  CoalPipingHistory> coalPipeHistoryEntityList = coalPipingHistoryRepositoryA.findByHTimeBetween(be
                        .getBeginTimestamp(),
                be.getEndTimestamp());
        //TODO 根据数据库查询的数据，进行数据校验
        //TODO 如果一个数据都没有，则根据开始时间进行数据填充，每个5秒填充一个，数据为null
        //TODO 如果数据量大于0，则根据当前时间进行数据填充，最小点时间的数据向前填充，间隔5秒，直至填充的时间小于开始时间，最大点的时间向后填充，直至填充的时间大于结束时间
        //TODO 遍历所有数据，如果两个数据之间的时间间隔大于10秒，则根据间隔开始时间进行添加，每个5秒填充一个，直至填充时间大于间隔的结束时间
        if(coalPipeHistoryEntityList.size() == 0){
            coalPipeHistoryEntityList = handleExceptionHistoryList(be);
        }


        return coalPipingHistoryService.generateJsonStringByHistroyList(coalPipeHistoryEntityList, be
                .getBeginTimestamp(), be.getEndTimestamp());
    }

    /**
     *
     * @param be
     * @return
     */
    private List<? extends CoalPipingHistory> handleExceptionHistoryList(BE be){
        //TODO 根据数据库查询的数据，进行数据校验
        //TODO 如果一个数据都没有，则根据开始时间进行数据填充，每个5秒填充一个，数据为null
        //TODO 如果数据量大于0，则根据当前时间进行数据填充，最小点时间的数据向前填充，间隔5秒，直至填充的时间小于开始时间，最大点的时间向后填充，直至填充的时间大于结束时间
        //TODO 遍历所有数据，如果两个数据之间的时间间隔大于10秒，则根据间隔开始时间进行添加，每个5秒填充一个，直至填充时间大于间隔的结束时间

        List<CoalPipingHistory> list = new ArrayList<>();
            long endTimeForTemp = be.getBeginTimestamp().getTime();
            long endTimeMillis = be.getEndTimestamp().getTime() - 1000 * 10;
            while (endTimeForTemp < endTimeMillis) {
                CoalPipingHistory e1 = new AcoalPipingHistoryEntity();
                e1.sethTime(new Timestamp(endTimeForTemp));
//                e1.sethPipeADencity((float) (Math.random()*10) *0  +  -1);
//                e1.sethPipeBDencity((float) (Math.random()*10) *0 +  -1);
//                e1.sethPipeCDencity((float) (Math.random()*10) *0 +  -1);
//                e1.sethPipeDDencity((float) (Math.random()*10) *0 +  -1);
//                e1.sethPipeAVelocity((float) (Math.random()*10) *0 +  -1);
//                e1.sethPipeBVelocity((float) (Math.random()*10) *0 +  -1);
//                e1.sethPipeCVelocity((float) (Math.random()*10) *0 +  -1);
//                e1.sethPipeDVelocity((float) (Math.random()*10) *0 +  -1);
                list.add(e1);
                endTimeForTemp += 5 * 1000;
            }

        return list;
    }
    /**
     * 获取四台磨的密度与风速的实时数据
     *
     * @return
     */
    @RequestMapping(value = "/getAllMillRealTimeData", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllMillRealTimeData() {
        //TODO 使用视图进行查询，避免一次性查询16个表的实时数据
        //todo 根据前台反馈的最新时间，返回最新的数据，如果没有，需要重新制定返回规则！！！！！！
        CoalPipingEntity coalMillAPiping1Entity = coalPipingRepository.findOne(11L);
        CoalPipingEntity coalMillAPiping2Entity = coalPipingRepository.findOne(12L);
        CoalPipingEntity coalMillAPiping3Entity = coalPipingRepository.findOne(13L);
        CoalPipingEntity coalMillAPiping4Entity = coalPipingRepository.findOne(14L);

        CoalPipingEntity coalMillBPiping1Entity = coalPipingRepository.findOne(21L);
        CoalPipingEntity coalMillBPiping2Entity = coalPipingRepository.findOne(22L);
        CoalPipingEntity coalMillBPiping3Entity = coalPipingRepository.findOne(23L);
        CoalPipingEntity coalMillBPiping4Entity = coalPipingRepository.findOne(24L);

        CoalPipingEntity coalMillCPiping1Entity = coalPipingRepository.findOne(31L);
        CoalPipingEntity coalMillCPiping2Entity = coalPipingRepository.findOne(32L);
        CoalPipingEntity coalMillCPiping3Entity = coalPipingRepository.findOne(33L);
        CoalPipingEntity coalMillCPiping4Entity = coalPipingRepository.findOne(34L);

        CoalPipingEntity coalMillDPiping1Entity = coalPipingRepository.findOne(41L);
        CoalPipingEntity coalMillDPiping2Entity = coalPipingRepository.findOne(42L);
        CoalPipingEntity coalMillDPiping3Entity = coalPipingRepository.findOne(43L);
        CoalPipingEntity coalMillDPiping4Entity = coalPipingRepository.findOne(44L);


        JSONObject jsonObject = new JSONObject();
        JSONObject millAPipe1JsonObj = getMillPipingJsonObj(coalMillAPiping1Entity);
        JSONObject millAPipe2JsonObj = getMillPipingJsonObj(coalMillAPiping2Entity);
        JSONObject millAPipe3JsonObj = getMillPipingJsonObj(coalMillAPiping3Entity);
        JSONObject millAPipe4JsonObj = getMillPipingJsonObj(coalMillAPiping4Entity);

        JSONObject millBPipe1JsonObj = getMillPipingJsonObj(coalMillBPiping1Entity);
        JSONObject millBPipe2JsonObj = getMillPipingJsonObj(coalMillBPiping2Entity);
        JSONObject millBPipe3JsonObj = getMillPipingJsonObj(coalMillBPiping3Entity);
        JSONObject millBPipe4JsonObj = getMillPipingJsonObj(coalMillBPiping4Entity);

        JSONObject millCPipe1JsonObj = getMillPipingJsonObj(coalMillCPiping1Entity);
        JSONObject millCPipe2JsonObj = getMillPipingJsonObj(coalMillCPiping2Entity);
        JSONObject millCPipe3JsonObj = getMillPipingJsonObj(coalMillCPiping3Entity);
        JSONObject millCPipe4JsonObj = getMillPipingJsonObj(coalMillCPiping4Entity);

        JSONObject millDPipe1JsonObj = getMillPipingJsonObj(coalMillDPiping1Entity);
        JSONObject millDPipe2JsonObj = getMillPipingJsonObj(coalMillDPiping2Entity);
        JSONObject millDPipe3JsonObj = getMillPipingJsonObj(coalMillDPiping3Entity);
        JSONObject millDPipe4JsonObj = getMillPipingJsonObj(coalMillDPiping4Entity);


        JSONObject millAJsonObj = new JSONObject();
        JSONObject millBJsonObj = new JSONObject();
        JSONObject millCJsonObj = new JSONObject();
        JSONObject millDJsonObj = new JSONObject();
        millAJsonObj.put("pipe1", millAPipe1JsonObj);
        millAJsonObj.put("pipe2", millAPipe2JsonObj);
        millAJsonObj.put("pipe3", millAPipe3JsonObj);
        millAJsonObj.put("pipe4", millAPipe4JsonObj);

        millBJsonObj.put("pipe1", millBPipe1JsonObj);
        millBJsonObj.put("pipe2", millBPipe2JsonObj);
        millBJsonObj.put("pipe3", millBPipe3JsonObj);
        millBJsonObj.put("pipe4", millBPipe4JsonObj);

        millCJsonObj.put("pipe1", millCPipe1JsonObj);
        millCJsonObj.put("pipe2", millCPipe2JsonObj);
        millCJsonObj.put("pipe3", millCPipe3JsonObj);
        millCJsonObj.put("pipe4", millCPipe4JsonObj);

        millDJsonObj.put("pipe1", millDPipe1JsonObj);
        millDJsonObj.put("pipe2", millDPipe2JsonObj);
        millDJsonObj.put("pipe3", millDPipe3JsonObj);
        millDJsonObj.put("pipe4", millDPipe4JsonObj);

        jsonObject.put("time", coalMillAPiping1Entity.getpTime().getTime());
        jsonObject.put("millA", millAJsonObj);
        jsonObject.put("millB", millBJsonObj);
        jsonObject.put("millC", millCJsonObj);
        jsonObject.put("millD", millDJsonObj);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/getMillARealTimeData", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMillARealTimeData() {
        CoalPipingEntity coalMillAPiping1Entity = coalPipingRepository.findOne(11L);
        CoalPipingEntity coalMillAPiping2Entity = coalPipingRepository.findOne(12L);
        CoalPipingEntity coalMillAPiping3Entity = coalPipingRepository.findOne(13L);
        CoalPipingEntity coalMillAPiping4Entity = coalPipingRepository.findOne(14L);
        //TODO 查询当前的锅炉负荷


        JSONObject jsonObject = new JSONObject();
        JSONObject millAPipe1JsonObj = getMillPipingJsonObj(coalMillAPiping1Entity);
        JSONObject millAPipe2JsonObj = getMillPipingJsonObj(coalMillAPiping2Entity);
        JSONObject millAPipe3JsonObj = getMillPipingJsonObj(coalMillAPiping3Entity);
        JSONObject millAPipe4JsonObj = getMillPipingJsonObj(coalMillAPiping4Entity);


        JSONObject millAJsonObj = new JSONObject();
        millAJsonObj.put("pipe1", millAPipe1JsonObj);
        millAJsonObj.put("pipe2", millAPipe2JsonObj);
        millAJsonObj.put("pipe3", millAPipe3JsonObj);
        millAJsonObj.put("pipe4", millAPipe4JsonObj);
        jsonObject.put("time", coalMillAPiping1Entity.getpTime().getTime());
        //TODO 当前负荷生成一个json对象
        jsonObject.put("millData", (int) (Math.random() * 100) + 30);
        jsonObject.put("millA", millAJsonObj);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/getMillRealTimeData", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMillRealTimeData(@RequestParam(value = "mill", required = false) String mill) {
        CoalPipingEntity coalMillPiping1Entity, coalMillPiping2Entity, coalMillPiping3Entity, coalMillPiping4Entity;
        JSONObject jsonObject = new JSONObject();
        if (null != mill) {
            long pipe1Id = 11L, pipe2Id = 12L, pipe3Id = 13L, pipe4Id = 14L;
            if (mill.equals("A")) {

            } else if (mill.equals("B")) {
                pipe1Id = 21L;
                pipe2Id = 22L;
                pipe3Id = 23L;
                pipe4Id = 24L;
            } else if (mill.equals("C")) {
                pipe1Id = 31L;
                pipe2Id = 32L;
                pipe3Id = 33L;
                pipe4Id = 34L;
            } else if (mill.equals("D")) {
                pipe1Id = 41L;
                pipe2Id = 42L;
                pipe3Id = 43L;
                pipe4Id = 44L;
            }
            coalMillPiping1Entity = coalPipingRepository.findOne(pipe1Id);
            coalMillPiping2Entity = coalPipingRepository.findOne(pipe2Id);
            coalMillPiping3Entity = coalPipingRepository.findOne(pipe3Id);
            coalMillPiping4Entity = coalPipingRepository.findOne(pipe4Id);


            //TODO 查询当前的锅炉负荷
            JSONObject millAPipe1JsonObj = getMillPipingJsonObj(coalMillPiping1Entity);
            JSONObject millAPipe2JsonObj = getMillPipingJsonObj(coalMillPiping2Entity);
            JSONObject millAPipe3JsonObj = getMillPipingJsonObj(coalMillPiping3Entity);
            JSONObject millAPipe4JsonObj = getMillPipingJsonObj(coalMillPiping4Entity);

            JSONObject millAJsonObj = new JSONObject();
            millAJsonObj.put("pipe1", millAPipe1JsonObj);
            millAJsonObj.put("pipe2", millAPipe2JsonObj);
            millAJsonObj.put("pipe3", millAPipe3JsonObj);
            millAJsonObj.put("pipe4", millAPipe4JsonObj);
            jsonObject.put("time", coalMillPiping1Entity.getpTime().getTime());
            //TODO 当前负荷生成一个json对象
            jsonObject.put("millData", (int) (Math.random() * 100) + 30);
            jsonObject.put("millA", millAJsonObj);
        }
        return jsonObject.toString();
    }

    private JSONObject getMillPipingJsonObj(CoalPipingEntity coalMillAPiping1Entity) {
        JSONObject millAPipe1JsonObj = new JSONObject();
        millAPipe1JsonObj.put("pipeId", coalMillAPiping1Entity.getId());
        millAPipe1JsonObj.put("pipeName", coalMillAPiping1Entity.getpName());
        millAPipe1JsonObj.put("density", coalMillAPiping1Entity.getpDencity());
        millAPipe1JsonObj.put("Velocity", coalMillAPiping1Entity.getpVelocity());
        return millAPipe1JsonObj;
    }

    /**
     * 获取A磨风速的实时的相对数据
     *
     * @return
     */
    @RequestMapping(value = "/getMillAVelocityRealTimeData", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMillAVelocityRealTimeData() {
        CoalPipingEntity coalPipingEntityForA = coalPipingRepository.findOne(11L);
        CoalPipingEntity coalPipingEntityForB = coalPipingRepository.findOne(12L);
        CoalPipingEntity coalPipingEntityForC = coalPipingRepository.findOne(13L);
        CoalPipingEntity coalPipingEntityForD = coalPipingRepository.findOne(14L);
        JSONObject jsonObject = new JSONObject();
        //TODO 相对值计算
        float avg = (coalPipingEntityForA.getpVelocity() + coalPipingEntityForB.getpVelocity() + coalPipingEntityForC.getpVelocity() + coalPipingEntityForD.getpVelocity()) / 4;

        jsonObject.put("time", coalPipingEntityForA.getpTime().getTime());
        JSONArray tempArrayData = new JSONArray();

        tempArrayData.put(coalPipingEntityForA.getpVelocity() - avg);
        tempArrayData.put(coalPipingEntityForB.getpVelocity() - avg);
        tempArrayData.put(coalPipingEntityForC.getpVelocity() - avg);
        tempArrayData.put(coalPipingEntityForD.getpVelocity() - avg);
        jsonObject.put("data", tempArrayData);
        return jsonObject.toString();
    }

    /**
     * 获取A磨风速的实时绝对数据
     *
     * @return
     */
    @RequestMapping(value = "/getMillAVelocityRealTimeDataForAbsolute", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMillAVelocityRealTimeDataForAbsolute() {
        CoalPipingEntity coalPipingEntityForA = coalPipingRepository.findOne(11L);
        CoalPipingEntity coalPipingEntityForB = coalPipingRepository.findOne(12L);
        CoalPipingEntity coalPipingEntityForC = coalPipingRepository.findOne(13L);
        CoalPipingEntity coalPipingEntityForD = coalPipingRepository.findOne(14L);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", coalPipingEntityForA.getpTime().getTime());
        JSONArray tempArrayData = new JSONArray();
        tempArrayData.put(coalPipingEntityForA.getpVelocity());
        tempArrayData.put(coalPipingEntityForB.getpVelocity());
        tempArrayData.put(coalPipingEntityForC.getpVelocity());
        tempArrayData.put(coalPipingEntityForD.getpVelocity());
        jsonObject.put("data", tempArrayData);
        return jsonObject.toString();
    }

    /**
     * 获取当前最近的时间
     *
     * @param time
     * @param time1
     * @param time2
     * @param time3
     * @return
     */
    private long getCurrentTime(long latestTime, long time, long time1, long time2, long time3) {
        //依据当前时间确定，如果寻找到最新的数据时间再10秒内，则时间为制定的time，否则为
        long now = new Date().getTime();
        Math.max(latestTime, time);
        return 1L;
    }

    /**
     * h获取A磨的15分钟历史浓度数据
     *
     * @return 返回json格式字符串，格式为 [{time:1111,data[ADensity,B,C,D]}]
     */
    @RequestMapping(value = "/getInitTimeData", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInitTimeData() {
        Timestamp laestTimestamp = getLatestTimeStamp();
        List<AcoalPipingHistoryEntity> list = coalPipingHistoryRepositoryA.findByHTimeAfterOrderByHTimeAsc(laestTimestamp);
        JSONArray jsonArray = generateInitDataJsonArray(list);
        return jsonArray.toString();
    }

    /**
     * 返回当前时间15分钟前的时间点
     * @return
     */
    private Timestamp getLatestTimeStamp(){
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - 15);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 根据millLocation，查询15分钟内的4根粉管的浓度数据
     * @param millLocation
     * @return
     */
    @RequestMapping(value="/getDensityRealTime15MinutesDataByMill",method=RequestMethod.GET,produces="text/html;" +
            "charset=UTF-8")
    @ResponseBody
    public String getDensityRealTime15MinutesDataByMill(@RequestParam(value="mill",required = false)String
                                                                millLocation){
        Timestamp latestTimeStamp = getLatestTimeStamp();
        List<? extends  CoalPipingHistory> list = findCoalPipingHistoryByMillTypeAndLastesTime(latestTimeStamp,
                millLocation);
        return generateDensityInitDataJsonArray(list).toString();
    }
    private List<? extends CoalPipingHistory> findCoalPipingHistoryByMillTypeAndLastesTime(Timestamp laestTimestamp,
                                                                                           String millLocation){
        List<? extends CoalPipingHistory> list = null;
        if(null != millLocation){
            if(millLocation.equals("A")){
                list = coalPipingHistoryRepositoryA.findByHTimeAfterOrderByHTimeAsc(laestTimestamp);
            }else if(millLocation.equals("B")){
                list = coalPipingHistoryRepositoryB.findByHTimeAfterOrderByHTimeAsc(laestTimestamp);
            }else if(millLocation.equals("C")){
                list = coalPipingHistoryRepositoryC.findByHTimeAfterOrderByHTimeAsc(laestTimestamp);
            }else if(millLocation.equals("D")){
                list = coalPipingHistoryRepositoryD.findByHTimeAfterOrderByHTimeAsc(laestTimestamp);
            }
        }
        return list;
    }
    /**
     * 根据millLocation，查询15分钟内的4根粉管的风速数据
     * @param millLocation
     * @return
     */
    @RequestMapping(value="/getVelocityRealTime15MinutesDataByMill",method=RequestMethod.GET,produces="text/html;" +
            "charset=UTF-8")
    @ResponseBody
    public String getVelocityRealTime15MinutesDataByMill(@RequestParam(value="mill",required = false)String
                                                                millLocation){
        Timestamp latestTimeStamp = getLatestTimeStamp();
        List<? extends  CoalPipingHistory> list = findCoalPipingHistoryByMillTypeAndLastesTime(latestTimeStamp,
                millLocation);
        return generateDensityInitDataJsonArray(list).toString();
    }

    /**
     * 查询15分钟内4根粉管的浓度与风速数据，磨煤机的数据
     * 实时画面使用到此方法
     * @param mill
     * @return
     */
    @RequestMapping(value = "/getInitTimeDataForDensity", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getInitTimeDataForDensity(@RequestParam(value = "mill", required = false) String mill) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTimeBefore = localDateTime.minusMinutes(15);
        JSONArray jsonArray = new JSONArray();
        if (null != mill) {
            List<? extends CoalPipingHistory> list = null;
            if (mill.equals("A")) {
                list = coalPipingHistoryRepositoryA.findByHTimeAfterOrderByHTimeAsc(Timestamp.valueOf(localDateTimeBefore));
            } else if (mill.equals("B")) {
                list = coalPipingHistoryRepositoryB.findByHTimeAfterOrderByHTimeAsc(Timestamp.valueOf(localDateTimeBefore));
            } else if (mill.equals("C")) {
                list = coalPipingHistoryRepositoryC.findByHTimeAfterOrderByHTimeAsc(Timestamp.valueOf(localDateTimeBefore));
            } else if (mill.equals("D")) {
                list = coalPipingHistoryRepositoryD.findByHTimeAfterOrderByHTimeAsc(Timestamp.valueOf(localDateTimeBefore));
            }
            //TODO 查询15分钟内的磨煤机数据
            List<Float> millHistoryList = new ArrayList<>();
            jsonArray = generateInitDataJsonArray(list, millHistoryList);
        }
        return jsonArray.toString();
    }

    private JSONArray generateInitDataJsonArray(List<? extends CoalPipingHistory> list, List<Float> millHistoryList) {
//        JSONArray jsonArray = generateInitDataJsonArray(list);
        JSONArray jsonArray = new JSONArray();

        if (null != list) {
            for (CoalPipingHistory coalPipingHistory : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("time", coalPipingHistory.gethTime().getTime());
                jsonObject.put("AD", coalPipingHistory.getPipeADensityNotNull());
                jsonObject.put("BD", coalPipingHistory.getPipeBDensityNotNull());
                jsonObject.put("CD", coalPipingHistory.getPipeCDensityNotNull());
                jsonObject.put("DD", coalPipingHistory.getPipeDDensityNotNull());

                jsonObject.put("AV", coalPipingHistory.getPipeAVelocityNotNull());
                jsonObject.put("BV", coalPipingHistory.getPipeBVelocityNotNull());
                jsonObject.put("CV", coalPipingHistory.getPipeCVelocityNotNull());
                jsonObject.put("DV", coalPipingHistory.getPipeDVelocityNotNull());

                jsonObject.put("m", (Math.random() * 100) + 20);

                jsonArray.put(jsonObject);
            }
            //TODO 添加锅炉负荷的15分钟数据
        }
        return jsonArray;
    }
    private JSONArray generateVelocityInitDataJsonArray(List<? extends  CoalPipingHistory> list){
        return generateDensityOrVelocityInitDataJsonArray(list,"velocity");
    }

    private JSONArray generateDensityInitDataJsonArray(List<? extends  CoalPipingHistory> list){
        return generateDensityOrVelocityInitDataJsonArray(list,"density");
    }

    private JSONArray generateDensityOrVelocityInitDataJsonArray(List<? extends CoalPipingHistory> list,String
            densityOrVelocity) {
        JSONArray jsonArray = new JSONArray();
        if(densityOrVelocity.equals("density")){
            jsonArray = generateInitDataJsonArray(list);
        }else if(densityOrVelocity.equals("velocity")){
            if (null != list) {
                for (CoalPipingHistory coalPipingHistory : list) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("time", coalPipingHistory.gethTime().getTime());
                    JSONArray jsonArrayForData = new JSONArray();
                    jsonArrayForData.put(coalPipingHistory.gethPipeAVelocity());
                    jsonArrayForData.put(coalPipingHistory.gethPipeBVelocity());
                    jsonArrayForData.put(coalPipingHistory.gethPipeCVelocity());
                    jsonArrayForData.put(coalPipingHistory.gethPipeDVelocity());
                    jsonObject.put("data", jsonArrayForData);

                    jsonArray.put(jsonObject);
                }
            }
        }

        return jsonArray;
    }


    private JSONArray generateInitDataJsonArray(List<? extends CoalPipingHistory> list) {
        JSONArray jsonArray = new JSONArray();
        if (null != list) {
            for (CoalPipingHistory coalPipingHistory : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("time", coalPipingHistory.gethTime().getTime());
                JSONArray jsonArrayForData = new JSONArray();
                jsonArrayForData.put(coalPipingHistory.gethPipeADencity());
                jsonArrayForData.put(coalPipingHistory.gethPipeBDencity());
                jsonArrayForData.put(coalPipingHistory.gethPipeCDencity());
                jsonArrayForData.put(coalPipingHistory.gethPipeDDencity());
                jsonObject.put("data", jsonArrayForData);

                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }

    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    pubic Date getBeginTime(){
        return beginTime;
    }*/
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sf, true));
    }


}


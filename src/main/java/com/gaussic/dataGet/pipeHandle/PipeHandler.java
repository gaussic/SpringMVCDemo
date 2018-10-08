package com.gaussic.dataGet.pipeHandle;

import com.gaussic.dataGet.windPojoHandle.PipingGetSingleDataThread;
import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.history.CoalPipingHistory;
import com.gaussic.repository.AlarmHistoryRepository;
import com.gaussic.repository.CoalPipingRepository;
import com.gaussic.repository.DeviceDcsRepository;
import com.gaussic.service.CoalMillService;
import com.gaussic.service.CoalPipingHistoryService;
import com.gaussic.service.CoalPipingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName PipeHandler
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/18 10:34
 * @Version 1.0
 */
@Component
public class PipeHandler {
    @Resource
    private CoalPipingRepository coalPipingRepository;
    @Autowired
    private CoalPipingService coalPipingService;
    @Autowired
    private CoalMillService coalMillService;
    @Autowired
    private CoalPipingHistoryService coalPipingHistoryService;

    @Autowired
    private AlarmHistoryRepository alarmHistoryRepository;

    /**
     * 粉管设备数据读取处理
     * @param localDateTime
     */
    public void handlePipe(LocalDateTime localDateTime) {
        try {
            //TODO 后期进行历史数据优化，例如长时间没有数据，可以对数据进行删除，或记录数据的状态。在查询的时候，不至于某一段时间没有数据
            //生成四条历史记录，每台磨煤机一条历史记录
            List<CoalMillEntity> coalMillEntityList = coalMillService.findAll();
            //遍历告警信息，将  ”粉管通讯中断”  的告警信息查询出来,并设置到coalPipingEntity中
            //查询当前粉管是否存在通讯中断告警，并且是正在告警中的状态。
            //设置完成后，coalPipingEntity将存在alarmHistoryEntity对象。
            coalPipingService.handleCoalPipingWithAlarmForCommunication(coalMillEntityList);
            Date now = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            List<CoalPipingHistory> coalPipingHistorieList = new ArrayList<>();
            new Thread(() -> {
                //访问远程网络，处理数据，录入数据库，更新数据库
                handlPipeUrlData(coalMillEntityList, coalPipingHistorieList, now);
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * DCS数据，读取处理
     * @param localDateTime
     */
//    /**
//     * @Description  DCS数据，读取数据
//     * @Author jiangyong xia
//     * @Date 10:57 18/9/3
//     * @Param
//     * @return
//     **/
//    private void handleDCS(LocalDateTime localDateTime) {
//        //一、读取DCS的数据
//        //01-查询DCS表
//        //02-根据DCS表，查询此设备的点设备地址
//        //03-根据设备信息，点号，读取数据
//        //04-分析数据，录入实时数据表
//        //05-分析数据，录入历史数据库
//        //二、写数据到DCS
//        //1、读取数据库中的pipe实时风速与密度
//        //2、将数据发送给DCS
//        try {
//            DeviceDcsPojo deviceDcsPojo = deviceDcsRepository.findOne(1l);
//            List<DevicePointPojo> devicePointPojoList = deviceDcsPojo.getDevicePointPojoList();
//            devicePointPojoList.stream().forEach((point) -> {
//                point.getDevicePointRealtimePojo();
//            });
//            int slaveId = Integer.parseInt(deviceDcsPojo.getDeviceAddress());
//            DevicePointHistory1Pojo devicePointHistory1Pojo = new DevicePointHistory1Pojo(localDateTime);
//            DevicePointHistory2Pojo devicePointHistory2Pojo = new DevicePointHistory2Pojo(localDateTime);
//            DevicePointHistory3Pojo devicePointHistory3Pojo = new DevicePointHistory3Pojo(localDateTime);
//            DevicePointHistory4Pojo devicePointHistory4Pojo = new DevicePointHistory4Pojo(localDateTime);
//            new Thread(() -> {
//                handelDcsData(devicePointPojoList, slaveId, localDateTime, devicePointHistory1Pojo, devicePointHistory2Pojo, devicePointHistory3Pojo, devicePointHistory4Pojo);
//            }).start();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 处理数据采集设备的数据，线程处理
     * @param coalMillEntityList
     * @param coalPipingHistorieList
     * @param now
     */
    private void handlPipeUrlData(List<CoalMillEntity> coalMillEntityList ,List<CoalPipingHistory>
            coalPipingHistorieList,Date now) {
        //TODO 使用16个线程，16根粉管的数据读取结束以后，进行数据的更新，历史数据的存储
        final CountDownLatch countDownLatch = new CountDownLatch(16);
        try {
            //01、使用16个线程读取与处理数据，并将处理后的数据更新到实体类中【未保存到数据库】。
            for (CoalMillEntity coalMillEntity : coalMillEntityList) {
                //生成一条coalPipingHistory数据
                CoalPipingHistory coalPipingHistory = coalPipingHistoryService.generatorHistory(coalMillEntity, now);
                coalPipingHistorieList.add(coalPipingHistory);
                List<CoalPipingEntity> coalPipingEntityList_temp = coalMillEntity.getCoalPipingEntityList();

                for (int m = 0; m < coalPipingEntityList_temp.size(); m++) {
                    CoalPipingEntity coalPipingEntity = coalPipingEntityList_temp.get(m);
                    String url = coalPipingEntity.getCoalPipingSetEntity().getsUrl();
                    final String urlFinal = new String(url);
                    new Thread(() -> {
                        PipingGetSingleDataThread pipingGetSingleDataThread = new PipingGetSingleDataThread();
                        pipingGetSingleDataThread.setCoalPipingEntity(coalPipingEntity);
                        pipingGetSingleDataThread.setNow(now);
                        //采集数据，并更新实时数据【不进行更新保存到数据库】
                        //TODO 历史数据录入，进行判定告警信息，此线程中，无法调用spring管理的bean
                        //TODO 粉管数据关键处理类
                        /************关键处理类****************/
                        pipingGetSingleDataThread.updateData(urlFinal);

                        countDownLatch.countDown();
                    }).start();

                }
            }

            //16个线程结束以后执行的代码
            countDownLatch.await();
            //02、将数据更新后的实体类，更新到数据库中。
            //02-01、更新coalPipingHistory数据
            for (CoalPipingHistory coalPipingHistory : coalPipingHistorieList) {
                coalPipingHistoryService.updateCoalPipingHistory(coalPipingHistory.gethCoalMillId(), coalPipingHistory);
            }
            //02-02、更新coalpipingEntity，粉管的浓度、密度、运行状态、告警状态
            //02-03、更新alarmHistoryEntity，告警信息
            for (CoalMillEntity coalMillEntity : coalMillEntityList) {
                List<CoalPipingEntity> coalPipingEntityList = coalMillEntity.getCoalPipingEntityList();
                for (CoalPipingEntity coalPipingEntity : coalPipingEntityList) {
                    coalPipingRepository.saveAndFlush(coalPipingEntity);
                    //告警信息的录入
                    if(null != coalPipingEntity){
                        if(null != coalPipingEntity.getAlarmHistoryEntity()){
                            alarmHistoryRepository.saveAndFlush(coalPipingEntity.getAlarmHistoryEntity());
                        }
                    }
                }
            }

//            coalPipingRepository.batchUpate(coalPipingEntityListAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

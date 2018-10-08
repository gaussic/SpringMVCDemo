package com.gaussic.dataGet.dcsHandle;

import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.dcs.*;
import com.gaussic.model.dcsRemote.DcsRemotePointPojo;
import com.gaussic.repository.*;
import com.gaussic.util.modbus4j.Modbus4jGetValues;
import com.gaussic.util.modbus4j.Modbus4jRtuMasterUtil;
import com.gaussic.util.modbus4j.Modbus4jSendValues;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName DCSGetDataByModbusRtuMaster
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/3 11:18
 * @Version 1.0
 */
@Component
public class DCSGetDataByModbusRtuMaster {
    @Autowired
    private DevicePointRealtimeRepository devicePointRealtimeRepository;
    @Autowired
    private DevicePointHistory1Repository devicePointHistory1Repository;
    @Autowired
    private DevicePointHistory2Repository devicePointHistory2Repository;
    @Autowired
    private DevicePointHistory3Repository devicePointHistory3Repository;
    @Autowired
    private DevicePointHistory4Repository devicePointHistory4Repository;
    @Autowired
    private CoalPipingRepository coalPipingRepository;
    @Autowired
    private DcsRemotePointRepository dcsRemotePointRepository;


    /**
     * @return
     * @Description DCS数据，读取数据
     * @Author jiangyong xia
     * @Date 10:57 18/9/3
     * @Param
     **/
    public void handleDCS(DeviceDcsPojo deviceDcsPojo, LocalDateTime localDateTime) {
        //一、读取DCS的数据
        //01-查询DCS表
        //02-根据DCS表，查询此设备的点设备地址
        //03-根据设备信息，点号，读取数据
        //04-分析数据，录入实时数据表
        //05-分析数据，录入历史数据库
        //二、写数据到DCS
        //1、读取数据库中的pipe实时风速与密度
        //2、将数据发送给DCS
        try {
            List<DevicePointPojo> devicePointPojoList = deviceDcsPojo.getDevicePointPojoList();
            //循环遍历，将懒加载的对象，提前进行遍历，防止在新的线程中找不到对象，造成空指针异常
            devicePointPojoList.forEach(DevicePointPojo::getDevicePointRealtimePojo);
            int slaveId = Integer.parseInt(deviceDcsPojo.getDeviceAddress());
            //生成新的DcsParam历史对象，封装下
            DcsParamHistoryPojo dcsParamHistoryPojo = generatorNewHistoryPojo(localDateTime);
            //读取与发送数据线程
            new Thread(() -> handelDcsData(deviceDcsPojo, devicePointPojoList, slaveId, localDateTime, dcsParamHistoryPojo)).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param devicePointPojoList
     * @param slaveId
     * @param localDateTime
     * @param devicePointHistory1Pojo
     * @param devicePointHistory2Pojo
     * @param devicePointHistory3Pojo
     * @param devicePointHistory4Pojo
     * @return
     * @Description //TODO 处理DCS数据，线程处理
     * @Author jiangyong xia
     * @Date 17:22 18/9/10
     **/
    private void handelDcsData(DeviceDcsPojo deviceDcsPojo, List<DevicePointPojo> devicePointPojoList, int slaveId, LocalDateTime localDateTime, DevicePointHistory1Pojo devicePointHistory1Pojo, DevicePointHistory2Pojo devicePointHistory2Pojo, DevicePointHistory3Pojo devicePointHistory3Pojo, DevicePointHistory4Pojo devicePointHistory4Pojo) {
//        DeviceDcsPojo deviceDcsPojo = deviceDcsRepository.findOne(1L);
        try {
            ModbusMaster modBusMaster = ModbusFacotryImplSecond.getModBusMasterByDeviceDcsPojo(deviceDcsPojo);
            /********读取数据，使用了新的线程*********/
            //方案一
//            getValue(modBusMaster, devicePointPojoList, slaveId, localDateTime, devicePointHistory1Pojo, devicePointHistory2Pojo, devicePointHistory3Pojo, devicePointHistory4Pojo);
            //方案二
            getValueWithSendManyValue(modBusMaster, devicePointPojoList, slaveId, localDateTime,
                    devicePointHistory1Pojo, devicePointHistory2Pojo, devicePointHistory3Pojo, devicePointHistory4Pojo);
            /********设置数据，使用了线程******************/
            //方案一
         //   sendValue(modBusMaster);
            //方案二
            sendValueWithSendManyValues(modBusMaster);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    /**
     * @return
     * @Description 生成新的历史参数对象
     * @Author jiangyong xia
     * @Date 17:19 18/9/10
     * @Param
     **/
    private DcsParamHistoryPojo generatorNewHistoryPojo(LocalDateTime localDateTime) {
        DevicePointHistory1Pojo devicePointHistory1Pojo = new DevicePointHistory1Pojo(localDateTime);
        DevicePointHistory2Pojo devicePointHistory2Pojo = new DevicePointHistory2Pojo(localDateTime);
        DevicePointHistory3Pojo devicePointHistory3Pojo = new DevicePointHistory3Pojo(localDateTime);
        DevicePointHistory4Pojo devicePointHistory4Pojo = new DevicePointHistory4Pojo(localDateTime);

        return new DcsParamHistoryPojo(devicePointHistory1Pojo, devicePointHistory2Pojo, devicePointHistory3Pojo,
                devicePointHistory4Pojo);
    }

    private void handelDcsData(DeviceDcsPojo deviceDcsPojo, List<DevicePointPojo> devicePointPojoList, int slaveId,
                               LocalDateTime localDateTime, DcsParamHistoryPojo dcsParamHistoryPojo) {
        handelDcsData(deviceDcsPojo, devicePointPojoList, slaveId, localDateTime, dcsParamHistoryPojo
                .getDevicePointHistory1Pojo(), dcsParamHistoryPojo.getDevicePointHistory2Pojo(), dcsParamHistoryPojo
                .getDevicePointHistory3Pojo(), dcsParamHistoryPojo.getDevicePointHistory4Pojo());
    }


    public void getValue(ModbusMaster modBusMaster, List<DevicePointPojo> devicePointPojoList, int slaveId,
                         LocalDateTime localDateTime, DcsParamHistoryPojo dcsParamHistoryPojo) {
        getValue(modBusMaster, devicePointPojoList, slaveId, localDateTime, dcsParamHistoryPojo
                .getDevicePointHistory1Pojo(), dcsParamHistoryPojo.getDevicePointHistory2Pojo(), dcsParamHistoryPojo
                .getDevicePointHistory3Pojo(), dcsParamHistoryPojo.getDevicePointHistory4Pojo());

    }

    public void getValueWithSendManyValue(ModbusMaster modBusMaster, List<DevicePointPojo> devicePointPojoList, int slaveId, LocalDateTime localDateTime, DevicePointHistory1Pojo devicePointHistory1Pojo, DevicePointHistory2Pojo devicePointHistory2Pojo, DevicePointHistory3Pojo devicePointHistory3Pojo, DevicePointHistory4Pojo devicePointHistory4Pojo) {
        if (null != modBusMaster) {
            devicePointPojoList.forEach((point) -> {
                if (null == point.getDevicePointRealtimePojo()) {
                    //新增一个devicePoingRealtimePojo对象，并保存到数据
                    handleAddAndSaveDevicePointRealtimePojo(point, localDateTime);
                }
            });
            Modbus4jGetValues.getModbusValueAndSetRealValue(modBusMaster, devicePointPojoList, slaveId);

            devicePointPojoList.forEach((point)->{
                DevicePointRealtimePojo devicePointRealtimePojo = point.getDevicePointRealtimePojo();
                //TODO 更新DCS的实时数据
                devicePointRealtimeRepository.saveAndFlush(devicePointRealtimePojo);
                //TODO 数据需要保存到数据库【未编写完成】
//                        devicePointRealtimePojoList.add(devicePointRealtimePojo);
                //TODO 更新历史数据的实体类，未更新到数据库
                DeviceDcsUtil.updateHistoryDate(point, devicePointRealtimePojo.getPointValue(), devicePointHistory1Pojo,
                        devicePointHistory2Pojo,
                        devicePointHistory3Pojo, devicePointHistory4Pojo);
            });
            devicePointHistory1Repository.saveAndFlush(devicePointHistory1Pojo);
            devicePointHistory2Repository.saveAndFlush(devicePointHistory2Pojo);
            devicePointHistory3Repository.saveAndFlush(devicePointHistory3Pojo);
            devicePointHistory4Repository.saveAndFlush(devicePointHistory4Pojo);
        }
    }

    /**
     * @return
     * @Description //TODO 从DCS获取数据
     * @Author jiangyong xia
     * @Date 17:27 18/9/10
     * @Param
     **/
    public void getValue(ModbusMaster modBusMaster, List<DevicePointPojo> devicePointPojoList, int slaveId, LocalDateTime localDateTime, DevicePointHistory1Pojo devicePointHistory1Pojo, DevicePointHistory2Pojo devicePointHistory2Pojo, DevicePointHistory3Pojo devicePointHistory3Pojo, DevicePointHistory4Pojo devicePointHistory4Pojo) {
        if (null != modBusMaster) {
            //TODO 使用线程锁定，线程的数量需要进行限制，当DCS的点数量多时，使用了一个modbus master 对象，会造成卡死现象。
            CountDownLatch countDownLatch = new CountDownLatch(devicePointPojoList.size());
            devicePointPojoList.forEach((point) -> {
                if (null == point.getDevicePointRealtimePojo()) {
                    //新增一个devicePoingRealtimePojo对象，并保存到数据
                    handleAddAndSaveDevicePointRealtimePojo(point, localDateTime);
                }
                DevicePointRealtimePojo devicePointRealtimePojo = point.getDevicePointRealtimePojo();
                new Thread(() -> {
                    getDataThreadRun(modBusMaster, point, slaveId, localDateTime, devicePointHistory1Pojo,
                            devicePointHistory2Pojo, devicePointHistory3Pojo, devicePointHistory4Pojo, devicePointRealtimePojo);
                    countDownLatch.countDown();
                }).start();

            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //TODO 更新DCS数据的实时数据
//        DevicePointRealtimeRepository.update
        //TODO 当modbusmaster返回为null是，需要将DCS数据，默认为0 录入到数据库中。
        devicePointHistory1Repository.saveAndFlush(devicePointHistory1Pojo);
        devicePointHistory2Repository.saveAndFlush(devicePointHistory2Pojo);
        devicePointHistory3Repository.saveAndFlush(devicePointHistory3Pojo);
        devicePointHistory4Repository.saveAndFlush(devicePointHistory4Pojo);

    }

    /**
     * @return
     * @Description //TODO 获取数据的线程主方法
     * @Author jiangyong xia
     * @Date 17:12 18/9/10
     * @Param
     **/
    private void getDataThreadRun(ModbusMaster modBusMaster, DevicePointPojo point, int slaveId, LocalDateTime
            localDateTime, DevicePointHistory1Pojo devicePointHistory1Pojo, DevicePointHistory2Pojo devicePointHistory2Pojo, DevicePointHistory3Pojo devicePointHistory3Pojo, DevicePointHistory4Pojo devicePointHistory4Pojo, DevicePointRealtimePojo devicePointRealtimePojo) {
        BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, Integer.parseInt(point.getPointAddress()), DataType.TWO_BYTE_INT_UNSIGNED);
        Number pointValue = null;
        try {
            //TODO 数据读取异常时，需要进行异常处理
            pointValue = modBusMaster.getValue(loc);
        } catch (ModbusTransportException e) {
//                        System.out.println("数据返回错误");
            String trackMessage = e.getMessage();
            if (trackMessage.contains("TimeoutException")) {
                //TODO 数据读取超时异常处理
//                            System.out.println("数据读取超时异常");
            }
//                            e.printStackTrace();
            System.out.println("异常处理");
        } catch (ErrorResponseException e) {
            //todo 异常处理
//                        System.out.println("ErrorResponseException 异常");
            e.printStackTrace();
        }
        if (null != pointValue) {
            //更新实时数据
            devicePointRealtimePojo.setPointValue(pointValue.floatValue());
            devicePointRealtimePojo.setrTime(Timestamp.valueOf(localDateTime));
            //TODO 更新DCS的实时数据
            devicePointRealtimeRepository.saveAndFlush(devicePointRealtimePojo);
            //TODO 数据需要保存到数据库【未编写完成】
//                        devicePointRealtimePojoList.add(devicePointRealtimePojo);
            //TODO 更新历史数据的实体类，未更新到数据库
            DeviceDcsUtil.updateHistoryDate(point, pointValue, devicePointHistory1Pojo, devicePointHistory2Pojo,
                    devicePointHistory3Pojo, devicePointHistory4Pojo);
        }
    }

    /**
     * @return
     * @Description //TODO 新增一个DevicePointRealTimePojo对象
     * @Author jiangyong xia
     * @Date 17:08 18/9/10
     * @Param
     **/
    private void handleAddAndSaveDevicePointRealtimePojo(DevicePointPojo point, LocalDateTime localDateTime) {
        DevicePointRealtimePojo devicePointRealtimePojoTemp = new DevicePointRealtimePojo();
        devicePointRealtimePojoTemp.setDevicePointPojo(point);
        devicePointRealtimePojoTemp.setrTime(Timestamp.valueOf(localDateTime));
        devicePointRealtimeRepository.saveAndFlush(devicePointRealtimePojoTemp);
        point.setDevicePointRealtimePojo(devicePointRealtimePojoTemp);
    }
    /**
     * @Description 更远DCSRemote的实时数据
     * @Author jiangyong xia
     * @Date 11:17 18/9/14
     * @Param
     * @return
     **/
    private void initDcsSendRemoteData(List<DcsRemotePointPojo> dcsRemotePointPojos){
        dcsRemotePointPojos.stream().forEach((dcsRemotePointPojo) -> {
            CoalPipingEntity coalPipingEntity = dcsRemotePointPojo.getCoalPipingEntity();
            //TODO 初始化，录入数据到数据库
            if (null != coalPipingEntity) {
                dcsRemotePointPojo.setCurrTime(coalPipingEntity.getpTime());
                if (dcsRemotePointPojo.getDensityOrVelocity() == 1) {
                    dcsRemotePointPojo.setCurrentValue(coalPipingEntity.getpVelocity());
                } else if (dcsRemotePointPojo.getDensityOrVelocity() == 0) {
                    dcsRemotePointPojo.setCurrentValue(coalPipingEntity.getpDencity());
                }
                dcsRemotePointRepository.saveAndFlush(dcsRemotePointPojo);
            }
        });
    }

    public void sendValueWithSendManyValues(ModbusMaster modbusMaster){
        if(null != modbusMaster){
            List<DcsRemotePointPojo> dcsRemotePointPojos = dcsRemotePointRepository.findAll();
            initDcsSendRemoteData(dcsRemotePointPojos);
            dcsRemotePointPojos.sort(Comparator.comparing(DcsRemotePointPojo::getAddress));
            int slaveId = dcsRemotePointPojos.get(0).getSlaveId();
            int startOffset = Integer.parseInt(dcsRemotePointPojos.get(0).getAddress());
            short[] data = getData(dcsRemotePointPojos);
            Modbus4jSendValues.sendValues(modbusMaster,slaveId,startOffset,data);
        }
    }

    private short[] getData(List<DcsRemotePointPojo> dcsRemotePointPojos) {
        short[] data = new short[dcsRemotePointPojos.size()];
        for(int i=0;i<dcsRemotePointPojos.size();i++){
            data[i] = dcsRemotePointPojos.get(i).getCurrentValue().shortValue();
        }
        return data;
    }

    /**
     * @return
     * @Description //TODO 发送管道数据到DCS设备
     * @Author jiangyong xia
     * @Date 17:28 18/9/10
     * @Param
     **/
    public void sendValue(ModbusMaster modBusMaster) {
        try {
            if (null != modBusMaster) {
                List<DcsRemotePointPojo> dcsRemotePointPojos = dcsRemotePointRepository.findAll();
                initDcsSendRemoteData(dcsRemotePointPojos);
                dcsRemotePointPojos.stream().forEach((dcsRemotePointPojo) -> {

                    //TODO 新建数据库表，DCS写入地址
                    //TODO 读取数据库总数据
                    //todo 采集粉管数据时，将实时数据录入到DCS实时数据中
                    int address = Integer.parseInt(dcsRemotePointPojo.getAddress());
                    int slaveIdForRemote = dcsRemotePointPojo.getSlaveId();
                    BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveIdForRemote, address, DataType.TWO_BYTE_INT_UNSIGNED);
                    try {
                        /*******************设置浓度与密度到远端DCS********************************/
                        modBusMaster.setValue(loc, dcsRemotePointPojo.getCurrentValue());
                    } catch (ModbusTransportException e) {
                        e.printStackTrace();
                    } catch (ErrorResponseException e) {
                        e.printStackTrace();
                    }

                });
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    private class DcsParamHistoryPojo {
        private DevicePointHistory1Pojo devicePointHistory1Pojo;
        private DevicePointHistory2Pojo devicePointHistory2Pojo;
        private DevicePointHistory3Pojo devicePointHistory3Pojo;
        private DevicePointHistory4Pojo devicePointHistory4Pojo;

        public DcsParamHistoryPojo() {
        }

        public DcsParamHistoryPojo(DevicePointHistory1Pojo devicePointHistory1Pojo, DevicePointHistory2Pojo devicePointHistory2Pojo, DevicePointHistory3Pojo devicePointHistory3Pojo, DevicePointHistory4Pojo devicePointHistory4Pojo) {
            this.devicePointHistory1Pojo = devicePointHistory1Pojo;
            this.devicePointHistory2Pojo = devicePointHistory2Pojo;
            this.devicePointHistory3Pojo = devicePointHistory3Pojo;
            this.devicePointHistory4Pojo = devicePointHistory4Pojo;
        }

        public DevicePointHistory1Pojo getDevicePointHistory1Pojo() {
            return devicePointHistory1Pojo;
        }

        public void setDevicePointHistory1Pojo(DevicePointHistory1Pojo devicePointHistory1Pojo) {
            this.devicePointHistory1Pojo = devicePointHistory1Pojo;
        }

        public DevicePointHistory2Pojo getDevicePointHistory2Pojo() {
            return devicePointHistory2Pojo;
        }

        public void setDevicePointHistory2Pojo(DevicePointHistory2Pojo devicePointHistory2Pojo) {
            this.devicePointHistory2Pojo = devicePointHistory2Pojo;
        }

        public DevicePointHistory3Pojo getDevicePointHistory3Pojo() {
            return devicePointHistory3Pojo;
        }

        public void setDevicePointHistory3Pojo(DevicePointHistory3Pojo devicePointHistory3Pojo) {
            this.devicePointHistory3Pojo = devicePointHistory3Pojo;
        }

        public DevicePointHistory4Pojo getDevicePointHistory4Pojo() {
            return devicePointHistory4Pojo;
        }

        public void setDevicePointHistory4Pojo(DevicePointHistory4Pojo devicePointHistory4Pojo) {
            this.devicePointHistory4Pojo = devicePointHistory4Pojo;
        }
    }
}

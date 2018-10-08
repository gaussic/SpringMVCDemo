package com.gaussic.dataGet.dcsHandle;

import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.dcs.DeviceDcsPojo;
import com.gaussic.model.dcs.DevicePointPojo;
import com.gaussic.model.dcs.DevicePointRealtimePojo;
import com.gaussic.model.dcsRemote.DcsRemotePointPojo;
import com.gaussic.repository.*;
import com.gaussic.util.modbus4j.Modbus4jSendValues;
import com.gaussic.util.modbus4j.RtuSerialPortWrapper;
import com.serotonin.modbus4j.*;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.serial.SerialPortWrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ClassName DCSGetDataByModbusRtuSlave，当前类在项目启动以后，启动一次
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/19 14:36
 * @Version 1.0
 */
@Component
public class DCSGetDataByModbusRtuSlave implements InitializingBean {

    private static ModbusSlaveSet modbusSlaveSet;
    private static BasicProcessImage processImage;

    @Autowired
    private DeviceDcsRepository deviceDcsRepository;
    @Autowired
    private DcsRemotePointRepository dcsRemotePointRepository;
    @Autowired
    private CoalPipingRepository coalPipingRepository;
    @Autowired
    private DevicePointRealtimeRepository devicePointRealtimeRepository;
    @Autowired
    private DevicePointRepository devicePointRepository;

    @Override
    public void afterPropertiesSet() {
        System.out.println("初始化执行一次————————————————————————————————");
        DeviceDcsPojo deviceDcsPojo = deviceDcsRepository.findOne(1L);
        //TODO 从站地址，固定为1
        int slaveId = 1;
        SerialPortWrapper wrapper = generatorSerialPortWrapper(deviceDcsPojo);
        modbusSlaveSet = new ModbusFactory().createRtuSlave(wrapper);
        processImage = initProcessImage(slaveId);
        modbusSlaveSet.addProcessImage(processImage);

        new Thread(() -> {
            try {
                modbusSlaveSet.start();
            } catch (ModbusInitException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private SerialPortWrapper generatorSerialPortWrapper(DeviceDcsPojo deviceDcsPojo) {
        String commPortId = "COM" + deviceDcsPojo.getDevicePort();
        commPortId = "COM2";
        int baudRate = deviceDcsPojo.getDeviceBoundRate();
        int flowControlIn = deviceDcsPojo.getDeviceFlowControlIn();
        int flowControlOut = deviceDcsPojo.getDeviceFlowControlOut();
        int dataBits = deviceDcsPojo.getDeviceDataBits();
        int stopBits = deviceDcsPojo.getDeviceStopBits();
        int parity = deviceDcsPojo.getDeviceParity();


        SerialPortWrapper wrapper = new RtuSerialPortWrapper(commPortId, baudRate, flowControlIn, flowControlOut,
                dataBits, stopBits, parity);
        return wrapper;
    }

    private BasicProcessImage initProcessImage(int slaveId) {
        BasicProcessImage processImage = new BasicProcessImage(slaveId);
        processImage.setHoldingRegister(100, (short) 1);

        ProcessImageListener processImageListener = generatorProcessImageLisener();

        initProcessImageData(processImage);



        processImage.addListener(processImageListener);
        return processImage;

    }

    private void initProcessImageData(BasicProcessImage processImage) {
        for(int i=100;i<200;i++){
            processImage.setHoldingRegister(i,(short)(Math.random()* 100));
        }
    }

    private ProcessImageListener generatorProcessImageLisener() {
        ProcessImageListener processImageListener = new ProcessImageListener() {
            @Override
            public void coilWrite(int offset, boolean oldValue, boolean newValue) {

            }

            @Override
            public void holdingRegisterWrite(int offset, short oldValue, short newValue) {
                //TODO 处理DCS发送过来的数据
                List<DevicePointPojo> deviceDcsPojoList = devicePointRepository.findByPointAddress(String.valueOf(offset));
                if(null != deviceDcsPojoList &&deviceDcsPojoList.size() >0) {
                    DevicePointPojo devicePointPojo = deviceDcsPojoList.get(0);
                    List<DevicePointRealtimePojo> devicePointRealtimePojoList = devicePointRealtimeRepository
                            .findByDevicePointPojo(devicePointPojo);
                    if(null != devicePointRealtimePojoList && devicePointRealtimePojoList.size() >0){
                        DevicePointRealtimePojo devicePointRealtimePojo = devicePointRealtimePojoList.get(0);
                        //TODO 时间需要进行设定
//                        devicePointRealtimePojo.setrTime();
                        devicePointRealtimePojo.setPointValue((float) newValue);
                        devicePointRealtimeRepository.saveAndFlush(devicePointRealtimePojo);
                        //TODO 保存历史数据，未编写
                    }
                }
            }
        };
        return processImageListener;
    }


    public void updatePipeDensityAndVelocity() {
        synchronized (modbusSlaveSet) {
            //TODO slaveId 固定为1
            ProcessImage processImage = modbusSlaveSet.getProcessImage(1);

            List<DcsRemotePointPojo> dcsRemotePointPojos = dcsRemotePointRepository.findAll();
            List<CoalPipingEntity> coalPipingEntityList = coalPipingRepository.findAll();
            updateDcsRemoteValues(dcsRemotePointPojos, coalPipingEntityList);

            dcsRemotePointPojos.forEach((dcsRemotePointPojo -> {
                processImage.setHoldingRegister(Integer.parseInt(dcsRemotePointPojo.getAddress()), dcsRemotePointPojo
                        .getCurrentValue().shortValue());
            }));
        }
    }

    /**
     * @return
     * @Description 更新dcsRemote的数据
     * @Author jiangyong xia
     * @Date 13:29 18/9/20
     * @Param
     **/
    public List<DcsRemotePointPojo> updateDcsRemoteValues(List<DcsRemotePointPojo> dcsRemotePointPojoList, List<CoalPipingEntity>
            coalPipingEntityList) {
        dcsRemotePointPojoList.forEach((dcsRemotePointPojo -> {
            coalPipingEntityList.forEach((coalPipingEntity -> {
                if (coalPipingEntity.getId() == dcsRemotePointPojo.getCoalPipingEntity().getId()) {
                    dcsRemotePointPojo.setCurrTime(coalPipingEntity.getpTime());
                    if (dcsRemotePointPojo.getDensityOrVelocity() == 1) {
                        dcsRemotePointPojo.setCurrentValue(coalPipingEntity.getpVelocity());
                    } else if (dcsRemotePointPojo.getDensityOrVelocity() == 0) {
                        dcsRemotePointPojo.setCurrentValue(coalPipingEntity.getpDencity());
                    }
                    dcsRemotePointRepository.saveAndFlush(dcsRemotePointPojo);
                }
            }));
        }));
        return dcsRemotePointPojoList;
    }

}

package com.gaussic.dataGet;

import com.gaussic.dataGet.dcsHandle.DCSGetDataByModbusRtuMaster;
import com.gaussic.dataGet.dcsHandle.DCSGetDataByModbusRtuSlaveSetData;
import com.gaussic.dataGet.pipeHandle.PipeHandler;
import com.gaussic.dataGet.windPojoHandle.PipingGetSingleDataThread;
import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.dcs.*;
import com.gaussic.model.history.CoalPipingHistory;

import com.gaussic.repository.*;
import com.gaussic.service.CoalMillService;
import com.gaussic.service.CoalPipingHistoryService;
import com.gaussic.service.CoalPipingService;

import com.serotonin.modbus4j.ModbusMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class PipingGetData extends Thread {
    @Autowired
    private DeviceDcsRepository deviceDcsRepository;
    @Autowired
    private DCSGetDataByModbusRtuMaster dcsGetDataByModbusRtuMaster;
    @Autowired
    private DCSGetDataByModbusRtuSlaveSetData dcsGetDataByModbusRtuSlaveSetData;
    @Autowired
    private PipeHandler pipeHandler;


    /**
     * 核心处理数据采集方法
     */
    public void getData() {
        LocalDateTime localDateTime = LocalDateTime.now();
        /****rtu Master 模式 处理DCS数据*****/
        DeviceDcsPojo deviceDcsPojo = deviceDcsRepository.findOne(1L);
        dcsGetDataByModbusRtuMaster.handleDCS(deviceDcsPojo,localDateTime);
        /**rtuSlave模式 处理 DCS数据**/
        dcsGetDataByModbusRtuSlaveSetData.updatePipeDensityAndVelocity();
        /***处理粉管数据***/
        pipeHandler.handlePipe(localDateTime);
    }





    @Override
    public void run() {
        this.getData();
    }







}

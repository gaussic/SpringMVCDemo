package com.gaussic.dataGet.dcsHandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName DCSGetDataByModbusRtuSlaveSetData
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/20 11:17
 * @Version 1.0
 */
@Component
public class DCSGetDataByModbusRtuSlaveSetData {

    @Autowired
    private DCSGetDataByModbusRtuSlave dcsGetDataByModbusRtuSlave;

    public void updatePipeDensityAndVelocity(){
        dcsGetDataByModbusRtuSlave.updatePipeDensityAndVelocity();
    }
}

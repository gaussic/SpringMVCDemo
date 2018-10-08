package com.gaussic.util.modbus4j;

import com.gaussic.model.dcs.DevicePointPojo;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.Modbus;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Modbus4jGetValues
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/14 9:55
 * @Version 1.0
 */
public class Modbus4jGetValues {

    public static void getModbusValueAndSetRealValue(ModbusMaster master,List<DevicePointPojo> devicePointPojoList,int
            slaveId){
        Map<Number,Number> map = getValues(master,devicePointPojoList,slaveId);
        devicePointPojoList.forEach((p)->{
            Number key = Integer.parseInt(p.getPointAddress());
            Number value = map.get(key);
            p.getDevicePointRealtimePojo().setPointValue(value.floatValue());
        });
    }


    public static Map<Number,Number> getValues(ModbusMaster master, List<DevicePointPojo> devicePointPojoList,int
            slaveId){
        List<BaseLocator<Number>> locatorList = new ArrayList<>();
        devicePointPojoList.forEach((p)->{
            BaseLocator<Number> baseLocator =BaseLocator.holdingRegister(slaveId, Integer.parseInt(p.getPointAddress()), DataType
                    .TWO_BYTE_INT_UNSIGNED);
            locatorList.add(baseLocator);
        });
        return getValues(master,locatorList);
    }


    public static Map<Number, Number> getValues(ModbusMaster master, List<BaseLocator<Number>> locatorList) {
        Map<Number, Number> map = new HashMap<>();
        BatchRead<Number> batch = new BatchRead<>();
        locatorList.forEach((l) -> {
            Number key = l.getOffset();
            batch.addLocator(key, l);
        });
        BatchResults<Number> result = null;
        try {
            result = master.send(batch);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
        BatchResults<Number> finalResult = result;
        locatorList.forEach((l) -> {
            Number key = l.getOffset();
            map.put(key, (Number) finalResult.getValue(key));
        });
        return map;
    }
}

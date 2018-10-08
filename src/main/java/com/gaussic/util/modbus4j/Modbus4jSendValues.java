package com.gaussic.util.modbus4j;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Modbus4jSendValues
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/14 9:55
 * @Version 1.0
 */
public class Modbus4jSendValues {
    public static ModbusResponse sendValues(ModbusMaster master,int slaveId, int startOffset, short[] data){

        WriteRegistersRequest request = null;
        try {
            request = new WriteRegistersRequest(slaveId, startOffset, data);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        ModbusResponse response = null;
        try {
            response = master.send(request);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        return response;
    }
}

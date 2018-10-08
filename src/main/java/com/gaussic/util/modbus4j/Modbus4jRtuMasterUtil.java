package com.gaussic.util.modbus4j;

import com.gaussic.model.dcs.DeviceDcsPojo;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;

public class Modbus4jRtuMasterUtil {
    public static ModbusMaster getModbusRtuMaster(String commPortId,int baudRate,int flowControlIn,int
            flowControlOut,int dataBits,int stopBits,int parity){
        RtuSerialPortWrapper wrapper = new RtuSerialPortWrapper(commPortId, baudRate, flowControlIn, flowControlOut,
                dataBits, stopBits, parity);

        ModbusMaster master = new ModbusFactory().createRtuMaster(wrapper);
        return master;
    }
    public static ModbusMaster getModbusRtuMaster(DeviceDcsPojo deviceDcsPojo){

        String comPortId = "COM" + String.valueOf(deviceDcsPojo.getDevicePort());
//        comPortId = "COM9";
        int baudRate = deviceDcsPojo.getDeviceBoundRate();
        int flowControlIn = deviceDcsPojo.getDeviceFlowControlIn();
        int flowControlOut = deviceDcsPojo.getDeviceFlowControlOut();
        int dataBits = deviceDcsPojo.getDeviceDataBits();
        int stopBits = deviceDcsPojo.getDeviceStopBits();
        int parity = deviceDcsPojo.getDeviceParity();
        return getModbusRtuMaster(comPortId, baudRate, flowControlIn, flowControlOut,
                dataBits, stopBits, parity);
    }

    public static void main(String[] args) {
        String commPortId = "COM2";
        int baudRate = 9600;
        int flowControlIn = 0;
        int flowControlOut = 0;
        int dataBits = 8;
        int stopBits = 1;
        int parity = 0;
        ModbusMaster modbusMaster = getModbusRtuMaster(commPortId, baudRate, flowControlIn, flowControlOut,
                dataBits, stopBits, parity);
        try {
            System.out.println(modbusMaster.getTimeout());
            modbusMaster.init();

            System.out.println(modbusMaster.isConnected());
            System.out.println(modbusMaster.isInitialized());
        } catch (ModbusInitException e) {
            System.out.println("modbus 生成异常" + e.getClass().getName());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        BaseLocator<Number> loc = BaseLocator.holdingRegister(1, Integer.parseInt("100"), DataType.TWO_BYTE_INT_UNSIGNED);
        try {
            Number number = modbusMaster.getValue(loc);
            System.out.println("number: " + number);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
    }
}

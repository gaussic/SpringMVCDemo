package com.modbus4j;

import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.*;

import java.util.Arrays;

public class Modbus4jDemo3 {
//    public static void main(String[] args) throws Exception {
//        String commPortId = "COM1";
//        int baudRate = 9600;
//        int flowControlIn = 0;
//        int flowControlOut = 0;
//        int dataBits = 8;
//        int stopBits = 1;
//        int parity = 0;
//
//        TestSerialPortWrapper wrapper = new TestSerialPortWrapper(commPortId, baudRate, flowControlIn, flowControlOut, dataBits, stopBits, parity);
//        ModbusMaster master = new ModbusFactory().createRtuMaster(wrapper);
//
//        SerialParameters params = new SerialParameters();
//        params.setCommPortId("COM1");
//        params.setBaudRate(9600);
//        params.setDataBits(8);
//        params.setStopBits(1);
//        params.setParity(0);
//
////        ModbusMaster master = new ModbusFactory().createRtuMaster(params);
//        master.init();
//
//        System.out.println(master.testSlaveNode(1));
//
//        // Define the point locator.
//        BaseLocator<Number> loc = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_INT_UNSIGNED);
////        BaseLocator<Number> loc = BaseLocator.holdingRegister(1, 0, DataType.);
//
//        // Set the point value
//        master.setValue(loc, 1800);
//
//        // Get the point value
//        System.out.println(master.getValue(loc));
//    }
}

package com.modbus4j;


import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
//import com.serotonin.modbus4j.serial.SerialPortWrapper;

import java.io.InputStream;
import java.io.OutputStream;

public class Modbus4jDemo5 {
    //设定MODBUS网络上从站地址
    private final static int SLAVE_ADDRESS = 1;
    // 串行波特率
    private final static int BAUD_RATE = 9600;

    public static void main(String[] args) {
        SerialParameters serialParameters = new SerialParameters();
        // 设定MODBUS通讯的串行口
        serialParameters.setCommPortId("COM3");
        // 设定成无奇偶校验
        serialParameters.setParity(0);
        // 设定成数据位是8位
        serialParameters.setDataBits(8);
        // 设定为1个停止位
        serialParameters.setStopBits(1);
        // 设定端口名称
        serialParameters.setPortOwnerName("Numb nuts");
        // 设定端口波特率
        serialParameters.setBaudRate(BAUD_RATE);


        // 创建ModbusFactory工厂实例
        ModbusFactory modbusFactory = new ModbusFactory();
        // 创建ModbusMaster实例
//        ModbusMaster master = modbusFactory.createRtuMaster(serialParameters);
        ModbusMaster master = modbusFactory.createRtuMaster(null);
        master.setTimeout(1000);
        master.setRetries(0);
        long start = System.currentTimeMillis();


        try {
            master.init();
        } catch (Exception e) {
            System.out.println( "Modbus Master Init Error: " + e.getMessage());
            return;
        }


        try {
//            System.out.println("Reg. 1001 Value:" + master.getValue(1, RegisterRange.HOLDING_REGISTER, 3110, DataType.FOUR_BYTE_FLOAT_SWAPPED));
        }
        finally {
            master.destroy();
        }

        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start) + "ms");


    }
}

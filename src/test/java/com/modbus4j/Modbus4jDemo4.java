package com.modbus4j;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;

public class Modbus4jDemo4 {
//    public static void main(String[] args)  {
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
//        master.setTimeout(200);
//        master.setRetries(1);
//        try {
//            master.init();
//        } catch (ModbusInitException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 1; i < 5; i++) {
//            long start = System.currentTimeMillis();
//            System.out.print("Testing " + i + "... ");
//            System.out.println(master.testSlaveNode(i));
//            System.out.println("Time: " + (System.currentTimeMillis() - start));
//        }
//
//         try {
//         System.out.println(master.send(new ReadHoldingRegistersRequest(1, 0, 1)));
//         }
//         catch (Exception e) {
//         e.printStackTrace();
//         }
//
//        // try {
//        // // ReadCoilsRequest request = new ReadCoilsRequest(2, 65534, 1);
//        // ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master
//        // .send(new ReadHoldingRegistersRequest(2, 0, 1));
//        // System.out.println(response);
//        // }
//        // catch (Exception e) {
//        // e.printStackTrace();
//        // }
//
//        // System.out.println(master.scanForSlaveNodes());
//
//        master.destroy();
//    }
}

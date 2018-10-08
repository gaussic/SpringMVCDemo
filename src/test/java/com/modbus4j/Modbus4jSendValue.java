package com.modbus4j;

import com.gaussic.util.modbus4j.RtuSerialPortWrapper;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.InvalidDataConversionException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.locator.BinaryLocator;
import com.serotonin.modbus4j.msg.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.random;

/**
 * @ClassName Modbus4jSendValue
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/5 15:55
 * @Version 1.0
 */
public class Modbus4jSendValue {
    public static void main(String[] args) {
        new Modbus4jSendValue().testSendValue();
    }

    public void testSendValue(){
        String comPortId = "COM" + 3;
//        comPortId = "COM9";
//        int baudRate = deviceDcsPojo.getDeviceBoundRate();
//        int flowControlIn = deviceDcsPojo.getDeviceFlowControlIn();
//        int flowControlOut = deviceDcsPojo.getDeviceFlowControlOut();
//        int dataBits = deviceDcsPojo.getDeviceDataBits();
//        int stopBits = deviceDcsPojo.getDeviceStopBits();
//        int parity = deviceDcsPojo.getDeviceParity();

        int baundRate = 9600;
        int flowControlIn = 0;
        int flowControlOut = 0;
        int dataBits = 8;
        int stopBits = 0;
        int parity = 0;

        RtuSerialPortWrapper wrapper = new RtuSerialPortWrapper(comPortId, baundRate, flowControlIn, flowControlOut,
                dataBits, stopBits, parity);

        ModbusMaster master = new ModbusFactory().createRtuMaster(wrapper);
        master.setTimeout(500);
        try {
            master.init();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        BaseLocator<Number> baseLocator =BaseLocator.holdingRegister(1, 104, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator2 =BaseLocator.holdingRegister(1, 101, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator3 =BaseLocator.holdingRegister(1, 102, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator4 =BaseLocator.holdingRegister(1, 103, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator5 =BaseLocator.holdingRegister(1, 104, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator6 =BaseLocator.holdingRegister(1, 105, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator7 =BaseLocator.holdingRegister(1, 106, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator8 =BaseLocator.holdingRegister(1, 107, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator9 =BaseLocator.holdingRegister(1, 108, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator10 =BaseLocator.holdingRegister(1, 109, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator11 =BaseLocator.holdingRegister(1, 110, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator12 =BaseLocator.holdingRegister(1, 111, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator13 =BaseLocator.holdingRegister(1, 112, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator14 =BaseLocator.holdingRegister(1, 113, DataType.TWO_BYTE_INT_UNSIGNED);
        BaseLocator<Number> baseLocator15 =BaseLocator.holdingRegister(1, 114, DataType.TWO_BYTE_INT_UNSIGNED);
        List<BaseLocator<Number>> locatorList = Arrays.asList(baseLocator,baseLocator2,baseLocator3,baseLocator4,
                baseLocator5,baseLocator6,baseLocator7,baseLocator8,baseLocator9,baseLocator10,baseLocator11,
                baseLocator12,baseLocator13,baseLocator14,baseLocator15);

        try {
//            locatorList.forEach((l)-> {
//                try {
//                    Number number = master.getValue(l);
//                    System.out.println("number:" + number);
//                } catch (ModbusTransportException e) {
//                    e.printStackTrace();
//                } catch (ErrorResponseException e) {
//                    e.printStackTrace();
//                }
//            });

           /* for(BaseLocator<Number> l:locatorList){
                Instant now = Instant.now();
                Number number = null;
                try {
                     number = master.getValue(l);
                } catch (ModbusTransportException e) {
                    e.printStackTrace();
                } catch (ErrorResponseException e) {
                    e.printStackTrace();
                }
                Instant later = Instant.now();
                Duration duration = Duration.between(later,now);
                System.out.println("number:" + number + "," + duration.getNano()/1000/1000+"," + master.getTimeout());
            }*/

           /* for(int i=0;i<locatorList.size();i++){
                BaseLocator<Number> l = locatorList.get(i);
                Instant now = Instant.now();
                Number number = null;
                try {
                    number = master.getValue(l);
                } catch (ModbusTransportException e) {
                    e.printStackTrace();
                } catch (ErrorResponseException e) {
                    e.printStackTrace();
                }
                Instant later = Instant.now();
                Duration duration = Duration.between(later,now);
                System.out.println(i+"," +"number:" + number + "," + duration.getNano()/1000/1000+"," + master
                        .getTimeout());
            }*/

           /*************************************getData方法 begin*************************************************/
            Instant now = Instant.now();
            BatchRead<String> batch = new BatchRead<>();
            locatorList.forEach((l)->batch.addLocator(String.valueOf(l.getOffset()),l));
//            batch.addLocator("", locator);
            BatchResults<String> result = master.send(batch);

            locatorList.forEach((l)-> System.out.println(result.getValue(String.valueOf(l.getOffset()))));
            Instant later = Instant.now();
            Duration duration = Duration.between(later,now);
            System.out.println( duration.getNano()/1000/1000+"," + master
                    .getTimeout());
            /*************************************getData方法 end*************************************************/


            master.setValue(baseLocator2,102.0f);



            short[] data = new short[locatorList.size()];
            List<Short> shortList = new ArrayList<>();
            locatorList.forEach((locator)->{
                Short value = (short)(Math.random()*100);
                System.out.println("v:" + value);
                shortList.add(value);
            });
            for(int i=0;i<shortList.size();i++){
                data[i] = shortList.get(i);
            }
            WriteRegistersRequest request = new WriteRegistersRequest(1, 100, data);
            ModbusResponse response = master.send(request);













        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
    }
}

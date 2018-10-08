package com.modbus4j;

import com.gaussic.util.modbus4j.RtuSerialPortWrapper;
import com.serotonin.modbus4j.*;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.exception.IllegalDataAddressException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.serial.SerialPortWrapper;
import com.serotonin.modbus4j.serial.rtu.RtuSlave;

/**
 * @ClassName Modbus4jSlaveDemo1
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/20 9:26
 * @Version 1.0
 */
public class Modbus4jSlaveDemo1 {
    //TEST VALUES
    private static Integer twoByteIntUnsignedSwapped = new Integer(29187); //Register 16
    private static Integer twoByteIntSignedSwapped = new Integer(-257); //Register 17
    private static Long fourByteIntUnsignedSwappedSwapped = new Long(16777216); //Register 18
    private static Long fourByteIntSignedSwappedSwapped = new Long(-16777217); //Register 20
    private static Long register22 = new Long(2369850368L); ////Register 22

    public static void main(String[] args) {
        String commPortId = "COM2";
        int baudRate = 9600;
        int flowControlIn = 0;
        int flowControlOut = 0;
        int dataBits = 8;
        int stopBits = 1;
        int parity = 0;

        int slaveId = 1;
        RtuSerialPortWrapper wrapper = new RtuSerialPortWrapper(commPortId, baudRate, flowControlIn, flowControlOut, dataBits, stopBits, parity);


        ModbusSlaveSet rtuSlave = new ModbusFactory().createRtuSlave(wrapper);
//        RtuSlave rtuSlave = new RtuSlave(wrapper);

        ProcessImageListener processImageListener = new ProcessImageListener() {
            @Override
            public void coilWrite(int offset, boolean oldValue, boolean newValue) {
                System.out.println("offset" + offset + ",oldValue:" + oldValue + ",newValue:" + newValue);
            }

            @Override
            public void holdingRegisterWrite(int offset, short oldValue, short newValue) {
                System.out.println("offset" + offset + ",oldValue:" + oldValue + ",newValue:" + newValue);
            }
        };
        BasicProcessImage processImage = new BasicProcessImage(slaveId);
        processImage.setHoldingRegister(100, new short[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
                9, 9, 9, 9, 9});
        processImage.addListener(processImageListener);

        rtuSlave.addProcessImage(processImage);

        new Thread(() -> {
            try {
                rtuSlave.start();
            } catch (ModbusInitException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            synchronized (rtuSlave) {
                try {
                    rtuSlave.wait(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (ProcessImage processImg : rtuSlave.getProcessImages()) {
                try {
                    updateProcessImage((BasicProcessImage) processImg);
                } catch (IllegalDataAddressException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /**
     * @Description 验证数据，是否存在错误，如果有跑出异常
     * @Author jiangyong xia
     * @Date 10:39 18/9/20
     * @Param
     * @return
     **/
    static void updateProcessImage(BasicProcessImage processImage) throws IllegalDataAddressException {

        int hr16Value = processImage.getNumeric(RegisterRange.HOLDING_REGISTER, 16, DataType.TWO_BYTE_INT_UNSIGNED_SWAPPED).intValue();
        if(hr16Value != twoByteIntUnsignedSwapped){
            throw new RuntimeException("Test failed on TWO_BYTE_INT_UNSIGNED_SWAPPED. Expected " + twoByteIntUnsignedSwapped + " but was: " + hr16Value);
        }

        short hr17Value = processImage.getNumeric(RegisterRange.HOLDING_REGISTER, 17, DataType.TWO_BYTE_INT_SIGNED_SWAPPED).shortValue();
        if(hr17Value != twoByteIntSignedSwapped){
            throw new RuntimeException("Test failed on TWO_BYTE_INT_SIGNED_SWAPPED. Expected " + twoByteIntSignedSwapped + " but was: " + hr17Value);
        }

        long hr18Value = processImage.getNumeric(RegisterRange.HOLDING_REGISTER, 18, DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED).longValue();
        if(hr18Value != fourByteIntUnsignedSwappedSwapped){
            throw new RuntimeException("Test failed on FOUR_BYTE_INT_UNSIGNED_SWAPPED_INVERTED. Expected " + fourByteIntUnsignedSwappedSwapped + "  but was: " + hr18Value);
        }

        int hr20Value = processImage.getNumeric(RegisterRange.HOLDING_REGISTER, 20, DataType.FOUR_BYTE_INT_SIGNED_SWAPPED_SWAPPED).intValue();
        if(hr20Value != fourByteIntSignedSwappedSwapped){
            throw new RuntimeException("Test failed on FOUR_BYTE_INT_SIGNED_SWAPPED_INVERTED. Expected  " +  fourByteIntSignedSwappedSwapped + "  but was: " + hr20Value);
        }

        long hr22Value = processImage.getNumeric(RegisterRange.HOLDING_REGISTER, 22, DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED_SWAPPED).longValue();
        if(hr22Value != register22){
            throw new RuntimeException("Test failed on FOUR_BYTE_INT_UNSIGNED_SWAPPED_INVERTED. Expected " + register22 + " but was: " + hr22Value);
        }

    }
}

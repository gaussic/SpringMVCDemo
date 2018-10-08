package com.gaussic.dataGet.dcsHandle;

import com.gaussic.model.dcs.DeviceDcsPojo;
import com.gaussic.util.modbus4j.Modbus4jRtuMasterUtil;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;

/**
 * @ClassName ModbusFacotryImplSecond
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/13 13:49
 * @Version 1.0
 */
public class ModbusFacotryImplSecond implements  ModbusFactoryI{
    public static ModbusMaster modbusMaster;

    /**
     * 单例获取modbusMaster对象
     *
     * @return
     */
    public static synchronized ModbusMaster getModBusMasterByDeviceDcsPojo(DeviceDcsPojo deviceDcsPojo) {
//        LocalTime now = LocalTime.now();
//        System.out.println("modbusMaster: geneator++++++++" + modbusMaster + ",      " + now);
        //TODO 连接出现异常时进行处理
        //TODO 出现异常的时候，需要记录日志
        if (modbusMaster == null) {
            modbusMaster = Modbus4jRtuMasterUtil.getModbusRtuMaster(deviceDcsPojo);
            try {
                modbusMaster.init();
            } catch (ModbusInitException e) {
                //TODO 记录 创建 modbusmaster 失败 ，返回 null
//                System.out.println("modbus 生成异常" + e.getClass().getName());
                String trackMessage = e.getMessage();
                if (trackMessage.contains("PortInUseException")) {
                    //todo 端口被占用异常处理
//                    System.out.println("端口被占用");
                }
                if (trackMessage.contains("NoSuchPortException")) {
                    //TODO 没有相应的端口异常处理，记录日志，记录到告警信息
//                    System.out.println("没有相应的端口映射");
                }
                modbusMaster = null;
                e.printStackTrace();
            }
        }
//        System.out.println("modbusMaster: geneator end-------" + modbusMaster + ",            " + now);
        return modbusMaster;
    }

    @Override
    public ModbusMaster getModBusMaster(DeviceDcsPojo deviceDcsPojo) {
        return getModBusMasterByDeviceDcsPojo(deviceDcsPojo);
    }
}

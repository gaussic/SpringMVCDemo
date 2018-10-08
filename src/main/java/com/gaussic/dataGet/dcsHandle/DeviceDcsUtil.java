package com.gaussic.dataGet.dcsHandle;

import com.gaussic.model.dcs.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DeviceDcsUtil {
    /**
     * 更新历史数据库数据
     *
     * @param point
     * @param pointValue
     * @param devicePointHistory1Pojo
     * @param devicePointHistory2Pojo
     * @param devicePointHistory3Pojo
     * @param devicePointHistory4Pojo
     */
    public static void updateHistoryDate(DevicePointPojo point, Number pointValue, DevicePointHistory1Pojo
            devicePointHistory1Pojo, DevicePointHistory2Pojo devicePointHistory2Pojo, DevicePointHistory3Pojo
                                                 devicePointHistory3Pojo, DevicePointHistory4Pojo devicePointHistory4Pojo) {
        //根据表的名称进行判断录入那个数据表
        //根据属性判断，使用反射设置方法
        String historyTableName = point.getPointHistoryDeviceTableName();
        String historyPojoProtertyName = point.getPointHistoryPorpertyName();
        try {
            String methodName = "set" + historyPojoProtertyName.substring(0, 1).toUpperCase() +
                    historyPojoProtertyName.substring(1);
            Object target = null;
            Class targetClass = null;
            if (historyTableName.equals(DevicePointHistory1Pojo.TABLE_NAME)) {
                target = devicePointHistory1Pojo;
                targetClass = DevicePointHistory1Pojo.class;
//                Method method = DevicePointHistory1Pojo.class.getMethod(methodName, Float.class);
//                method.invoke(devicePointHistory1Pojo, pointValue.floatValue());
            }else if(historyTableName.equals(DevicePointHistory2Pojo.TABLE_NAME)){
                target = devicePointHistory2Pojo;
                targetClass = DevicePointHistory2Pojo.class;
            }else if(historyTableName.equals(DevicePointHistory3Pojo.TABLE_NAME)){
                target = devicePointHistory3Pojo;
                targetClass = DevicePointHistory3Pojo.class;
            }else if(historyTableName.equals(DevicePointHistory4Pojo.TABLE_NAME)){
                target = devicePointHistory4Pojo;
                targetClass = DevicePointHistory4Pojo.class;
            }

            Method method = targetClass.getMethod(methodName,Float.class);
            method.invoke(target,pointValue.floatValue());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}

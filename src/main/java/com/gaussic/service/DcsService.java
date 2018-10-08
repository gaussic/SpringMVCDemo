package com.gaussic.service;

import com.gaussic.model.dcs.DeviceDcsPojo;
import com.gaussic.model.dcs.DevicePointPojo;
import com.gaussic.model.dcsRemote.DcsRemotePointPojo;
import com.gaussic.repository.DcsRemotePointRepository;
import com.gaussic.repository.DeviceDcsRepository;
import com.gaussic.repository.DevicePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DcsService
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/7 14:39
 * @Version 1.0
 */
@Component
public class DcsService {

    @Autowired
    private DeviceDcsRepository deviceDcsRepository;
    @Autowired
    private DevicePointRepository devicePointRepository;
    @Autowired
    private DcsRemotePointRepository dcsRemotePointRepository;

    private static List<String> deviceDcsAttrsList = new ArrayList<>();

    static {
        deviceDcsAttrsList.add("deviceName");
        deviceDcsAttrsList.add("deviceAddress");
        deviceDcsAttrsList.add("devicePort");
        deviceDcsAttrsList.add("deviceBoundRate");
        deviceDcsAttrsList.add("deviceNote");
        deviceDcsAttrsList.add("deviceNum");
        deviceDcsAttrsList.add("deviceFlowControlIn");
        deviceDcsAttrsList.add("deviceFlowControlOut");
        deviceDcsAttrsList.add("deviceDataBits");
        deviceDcsAttrsList.add("deviceStopBits");
        deviceDcsAttrsList.add("deviceParity");
    }

    private static List<String> devicePointAttrsList = new ArrayList<>();

    static {
        //TODO dcs point属性添加
        devicePointAttrsList.add("pointName");
        devicePointAttrsList.add("pointAddress");
        devicePointAttrsList.add("pointNote");
        devicePointAttrsList.add("pointHistoryDeviceTableName");
        devicePointAttrsList.add("pointHistoryColumnName");
        devicePointAttrsList.add("pointHistoryPorpertyName");
        devicePointAttrsList.add("pointHistoryOffset");
    }

    private static List<String> deviceRemoteAttrsList = new ArrayList<>();
    static {
        //TODO dcs 发送数据的pojo类的属性添加
        deviceRemoteAttrsList.add("address");
        deviceRemoteAttrsList.add("note");
        deviceRemoteAttrsList.add("densityOrVelocity");
        deviceRemoteAttrsList.add("slaveId");
        deviceRemoteAttrsList.add("remotePointName");
    }

    public void saveDeviceDcsWithMainAttr(DeviceDcsPojo deviceDcsPojo) {
        if (null != deviceDcsPojo && null != deviceDcsPojo.getDeviceId()) {
            DeviceDcsPojo deviceDcsPojoFromDb = deviceDcsRepository.findOne(deviceDcsPojo.getDeviceId());
            for (int i = 0; i < deviceDcsAttrsList.size(); i++) {
                setNewAttributeValue(deviceDcsPojo, deviceDcsPojoFromDb, deviceDcsAttrsList.get(i));
            }
            deviceDcsRepository.saveAndFlush(deviceDcsPojoFromDb);
        }
    }

    private <T> void setNewAttributeValue(T oldObj, T newObj, String attribute) {
        try {
            Class c = oldObj.getClass();
            String attr = getFirstUpperString(attribute);
            String setMethodName = "set" + attr;
            String getMethodName = "get" + attr;
            Method getMethod = c.getMethod(getMethodName);
            Class paramTypeClass = getMethod.getReturnType();
            Method setMethod = c.getMethod(setMethodName, paramTypeClass);
            if (!getMethod.invoke(oldObj).equals(getMethod.invoke(newObj))) {
//                paramTypeClass.getSimpleName()
                if (paramTypeClass.getSimpleName().equals("Integer")) {
                    setMethod.invoke(newObj, (Integer) getMethod.invoke(oldObj));
                } else if (paramTypeClass.getSimpleName().equals("String")) {
                    setMethod.invoke(newObj, (String) getMethod.invoke(oldObj));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void setNewAttributeValue(DeviceDcsPojo deviceDcsPojoOld, DeviceDcsPojo deviceDcsPojoNew, String attributeName) {
        try {
            Class c = deviceDcsPojoOld.getClass();
            String attr = getFirstUpperString(attributeName);
            String setMethodName = "set" + attr;
            String getMethodName = "get" + attr;
            Method getMethod = c.getMethod(getMethodName);
            Class paramTypeClass = getMethod.getReturnType();
            Method setMethod = c.getMethod(setMethodName, paramTypeClass);
            if (!getMethod.invoke(deviceDcsPojoOld).equals(getMethod.invoke(deviceDcsPojoNew))) {
//                paramTypeClass.getSimpleName()
                if(paramTypeClass.getSimpleName().equals("Integer")) {
                    setMethod.invoke(deviceDcsPojoNew,(Integer)getMethod.invoke(deviceDcsPojoOld));
                }else if(paramTypeClass.getSimpleName().equals("String")){
                    setMethod.invoke(deviceDcsPojoNew,(String)getMethod.invoke(deviceDcsPojoOld));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    private String getFirstUpperString(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1,
                str.length());
    }

    public void saveDevicePointWithMainAttr(DevicePointPojo devicePointPojo) {
        if (null != devicePointPojo && null != devicePointPojo.getPointId()) {
            DevicePointPojo devicePointPojoFromDb = devicePointRepository.findOne(devicePointPojo.getPointId());
            if (null != devicePointPojoFromDb) {
                devicePointAttrsList.forEach((p) -> setNewAttributeValue(devicePointPojo, devicePointPojoFromDb, p));
            }
            devicePointRepository.saveAndFlush(devicePointPojoFromDb);
        }
    }

    public void saveDevicePointWithMainAttr(DcsRemotePointPojo dcsRemotePointPojo) {
        if(null != dcsRemotePointPojo && null != dcsRemotePointPojo.getDcsRemotePointId()){
            DcsRemotePointPojo dcsRemotePointPojoFromDb = dcsRemotePointRepository.findOne(dcsRemotePointPojo.getDcsRemotePointId());
            if(null != dcsRemotePointPojoFromDb){
                deviceRemoteAttrsList.forEach((p)->setNewAttributeValue(dcsRemotePointPojo,dcsRemotePointPojoFromDb,p));
            }
            dcsRemotePointRepository.saveAndFlush(dcsRemotePointPojoFromDb);
        }
    }
}

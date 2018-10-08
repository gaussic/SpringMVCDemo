package com.gaussic;

import com.gaussic.model.dcs.DevicePointHistory1Pojo;

import java.lang.reflect.Method;

public class TestReflection {
    public static void main(String[] args) throws NoSuchMethodException {
//        DevicePointHistory1Pojo.setParam1
        String methodName = "setParam1";
        Method method = DevicePointHistory1Pojo.class.getMethod(methodName);

    }
}

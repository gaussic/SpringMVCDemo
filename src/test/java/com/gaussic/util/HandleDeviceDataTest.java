package com.gaussic.util;

import com.gaussic.dataGet.windPojoHandle.WindDataPojo;
import org.json.JSONArray;
import org.springframework.beans.BeanUtils;

public class HandleDeviceDataTest {
    public static void main(String[] args) {
        String url = "http://172.18.33.43:8080/pulver";
        JSONArray jo = HandleDeviceData.httpRequest(url, "GET");
        WindDataPojo windDataPojo = new WindDataPojo(jo);
        System.out.println(windDataPojo);



         String urlFinal = new String();
        BeanUtils.copyProperties(url,urlFinal);
        System.out.println("urlFinal:" + urlFinal);
    }
}

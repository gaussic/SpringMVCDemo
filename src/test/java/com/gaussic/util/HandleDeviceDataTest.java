package com.gaussic.util;

import com.gaussic.util.HandleDeviceData;
import org.json.JSONArray;

public class HandleDeviceDataTest {
    public static void main(String[] args) {
        String url = "http://192.168.1.41:8080/windJsonProject/json.jsp";
        JSONArray jo = HandleDeviceData.httpRequest(url, "GET");
    }
}

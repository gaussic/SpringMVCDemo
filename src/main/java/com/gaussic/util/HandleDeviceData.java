package com.gaussic.util;

import com.gaussic.dataGet.windPojoHandle.WindDataPojo;
import org.json.JSONArray;

import java.io.*;
import java.net.*;

public class HandleDeviceData {
    /**
     * 读取URL数据，返回JSONArray数据
     *
     * @param requestUrl
     * @param requestMethod
     * @return
     */
    public static JSONArray httpRequest(String requestUrl, String requestMethod) {
        JSONArray jsonArray;
        String jsonPStr = getUrlData(requestUrl, requestMethod);
        String jsonStr = handleJsonP(jsonPStr);
        jsonArray = new JSONArray(jsonStr);
        return jsonArray;
    }

    /**
     * jsonP数据转换为json数据
     *
     * @param jsonPStr
     * @return
     */
    private static String handleJsonP(String jsonPStr) {
        int startIndex = jsonPStr.indexOf("(");
        int endIndex = jsonPStr.lastIndexOf(")");
        String jsonStr = "" + jsonPStr.substring(startIndex + 1, endIndex) + "";
        return jsonStr;
    }

    /**
     * 读取URL数据，读取正常时，返回字符串
     * 读取失败时，返回异常字符串jsonp([])
     *
     * @param requestUrl
     * @param requestMethod
     * @return
     */
    private static String getUrlData(String requestUrl, String requestMethod) {
        StringBuffer buffer = new StringBuffer();
        String connectErrorMsg = "";
        String handleDataErrorMsg = "";
        boolean connectFlag = false;
//        try {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            //TODO IP地址格式不正确，需要正确的返回数据，并记录相应的数据
//            System.out.println("IP地址格式不正确：" + requestUrl);
            connectErrorMsg += "IP 地址解析错误";
//            e.printStackTrace();
        }
        // http协议传输
        HttpURLConnection httpUrlConn = null;
        try {
            httpUrlConn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
//            e.printStackTrace();
            //TODO 记录日志
            connectErrorMsg += "连接设备地址，IO异常";
        }
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);
        httpUrlConn.setUseCaches(false);
        // 设置请求方式（GET/POST）
        try {
            httpUrlConn.setRequestMethod(requestMethod);
        } catch (ProtocolException e) {
            //TODO 记录日志
            connectErrorMsg += "设置GET方法，异常";
//            e.printStackTrace();
        }
        if ("GET".equalsIgnoreCase(requestMethod)) {
            try {
                httpUrlConn.setConnectTimeout(1500);
                httpUrlConn.connect();
                connectFlag = true;
            } catch (NoRouteToHostException e) {
                //TODO 记录日志
                connectErrorMsg += "设备地址路由错误";
//                e.printStackTrace();
            } catch (SocketTimeoutException socketTimeoutException) {
                httpUrlConn.disconnect();
                //TODO 超时异常 ，记录日志
                connectErrorMsg += "连接超时异常1";
//                System.out.println("超时异常：connect timed out,连接地址：" + requestUrl);
            } catch (ConnectException connectException) {
                // 连接超时异常处理
                //TODO 记录日志
                connectErrorMsg += "连接超时异常2";
//                System.out.println("连接超时异常,连接地址：" + requestUrl);
//                System.out.println(httpUrlConn);
            } catch (SocketException e) {
                //TODO 记录日志
                connectErrorMsg += "SocketException异常";
//                e.printStackTrace();
            } catch (IOException s) {
                //TODO 记录日志
                connectErrorMsg += "连接异常，IOException";
            }
        }
        if (connectFlag) {
            // 将返回的输入流转换成字符串
            InputStream inputStream = null;
            try {
                inputStream = httpUrlConn.getInputStream();
            } catch (IOException e) {
                //TODO 记录日志
                handleDataErrorMsg += "数据流解析异常";
//                e.printStackTrace();
            }
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            } catch (UnsupportedEncodingException e) {
                //TODO 记录日志
                handleDataErrorMsg += "编码解析异常";
//                e.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            try {
                while ((str = bufferedReader.readLine()) != null) buffer.append(str);
            } catch (IOException ioException) {
                //TODO 记录日志
                handleDataErrorMsg += "流读取异常";
//                ioException.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                handleDataErrorMsg += "流关闭异常1";
//                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                //TODO 记录日常
                handleDataErrorMsg += "流关闭异常2";
//                e.printStackTrace();
            }
            // 释放资源
            try {
                inputStream.close();
            } catch (IOException e) {
                //TODO 记录日志
                handleDataErrorMsg += "流关闭异常3";
                e.printStackTrace();
            }
            inputStream = null;
            httpUrlConn.disconnect();
        } else {
            buffer.append("josnp([])");
        }

        //返回异常数据处理
        if (connectErrorMsg != null && connectErrorMsg.length() > 0) {
            String connectionError = "{\""+WindDataPojo.CONNECT_ERROR+"\":\"" + connectErrorMsg + "\"}";
            if(buffer.toString().length() > 9){
                connectionError = ","+ connectionError;
            }
            buffer.insert(buffer.toString().length() - 2,connectionError);
//            buffer.append(connectionError.toCharArray(),buffer.toString().length() - 2,connectionError.length());
        }
        if (handleDataErrorMsg != null && handleDataErrorMsg.length()> 0) {
            String handleError = "{\""+ WindDataPojo.HANDLE_DATA_ERROR+"\":\"" +handleDataErrorMsg +"\"}";
            if(buffer.toString().length() > 9){
                handleError = ","+ handleError;
            }
            buffer.insert(buffer.toString().length() - 2,handleError);
//            buffer.append(handleError.toCharArray(),buffer.toString().length() - 2,handleError.length());
        }

        System.out.println("buffer:" + buffer.toString());
        return buffer.toString();
    }
}

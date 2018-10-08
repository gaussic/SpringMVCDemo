package com.gaussic.controller;

import com.gaussic.model.dcs.DeviceDcsPojo;
import com.gaussic.model.dcs.DevicePointPojo;
import com.gaussic.model.dcsRemote.DcsRemotePointPojo;
import com.gaussic.repository.*;
import com.gaussic.service.DcsService;
import com.serotonin.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName DcsConfigController
 * @Description TODO
 * @Author jiangyong xia
 * @Date 18/9/6 9:27
 * @Version 1.0
 */
@Controller
public class DcsConfigController {

    @Autowired
    private DcsRemotePointRepository dcsRemotePointRepository;
    @Autowired
    private DeviceDcsRepository deviceDcsRepository;
    @Autowired
    private DevicePointRepository devicePointRepository;
    @Autowired
    private DevicePointHistory1Repository devicePointHistory1Repository;
    @Autowired
    private DevicePointHistory2Repository devicePointHistory2Repository;
    @Autowired
    private DevicePointHistory3Repository devicePointHistory3Repository;
    @Autowired
    private DevicePointHistory4Repository devicePointHistory4Repository;
    @Autowired
    private DevicePointRealtimeRepository devicePointRealtimeRepository;

    @Autowired
    private DcsService dcsService;

    /**
     * @Description dcs配置页面【查询】
     * @Author jiangyong xia
     * @Date 9:31 18/9/6
     * @Param
     * @return
     **/
    @RequestMapping(value="dcsConfigIndex",method= RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String dcsConfigIndex(){
        StringBuffer buffer = new StringBuffer();
        JSONObject resultJsonObject = new JSONObject();
        resultJsonObject.put("total",1);

        JSONArray jsonArray = new JSONArray();
        DeviceDcsPojo deviceDcsPojo = deviceDcsRepository.findOne(1L);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceId",deviceDcsPojo.getDeviceId());
        jsonObject.put("deviceName",deviceDcsPojo.getDeviceName());
        jsonObject.put("deviceAddress",deviceDcsPojo.getDeviceAddress());
        jsonObject.put("devicePort",deviceDcsPojo.getDevicePort());
        jsonObject.put("deviceBoundRate",deviceDcsPojo.getDeviceBoundRate());
        jsonObject.put("deviceLinkState",deviceDcsPojo.getDeviceLinkState());
        jsonObject.put("deviceNote",deviceDcsPojo.getDeviceNote());
        jsonObject.put("deviceNum",deviceDcsPojo.getDeviceNum());
        jsonObject.put("deviceFlowControlIn",deviceDcsPojo.getDeviceFlowControlIn());
        jsonObject.put("deviceFlowControlOut",deviceDcsPojo.getDeviceFlowControlOut());
        jsonObject.put("deviceDataBits",deviceDcsPojo.getDeviceDataBits());
        jsonObject.put("deviceStopBits",deviceDcsPojo.getDeviceStopBits());
        jsonObject.put("deviceParity",deviceDcsPojo.getDeviceParity());
        jsonArray.put(jsonObject);

        resultJsonObject.put("rows",jsonArray);
        return resultJsonObject.toString();
    }
    /**
     * @Description dcs修改配置【修改】
     * @Author jiangyong xia
     * @Date 9:33 18/9/6
     * @Param
     * @return
     **/
    @RequestMapping(value="dcsConfigUpdate",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String dcsConfigUpdate(DeviceDcsPojo deviceDcsPojo){
        StringBuffer buffer = new StringBuffer();
        System.out.println("deviceDcsPojo:" + deviceDcsPojo);
        buffer.append("success");
        JSONObject jsonObject = new JSONObject();

        dcsService.saveDeviceDcsWithMainAttr(deviceDcsPojo);

        jsonObject.put("data","success");
        return jsonObject.toString();
    }
    /**
     * @Description 远端获取DCS数据，配置【查询】
     * @Author jiangyong xia
     * @Date 9:37 18/9/6
     * @Param
     * @return
     **/
    @RequestMapping(value="remoteGetDataIndex" ,method=RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String remoteGetDataIndex(){
        List<DevicePointPojo> devicePointPojoList = devicePointRepository.findAll();
        JSONObject jsonObject = new JSONObject();

        if(null != devicePointPojoList && devicePointPojoList.size() >0) {
            jsonObject.put("total", devicePointPojoList.size());
            JSONArray jsonArray = new JSONArray();
            devicePointPojoList.forEach((p)->{
                JSONObject jo = new JSONObject();
                jo.put("pointId",p.getPointId());
                jo.put("dcsId",p.getDeviceDcsPojo().getDeviceId());
                jo.put("pointName",p.getPointName());
                jo.put("pointAddress",p.getPointAddress());
                jo.put("pointNote",p.getPointNote());
                jo.put("pointHistoryTableName",p.getPointHistoryDeviceTableName());
                jo.put("pointHistoryColumnName",p.getPointHistoryColumnName());
                jo.put("pointHistoryPropertyName",p.getPointHistoryPorpertyName());
                jo.put("pointHistoryOffset",p.getPointHistoryOffset());
                jsonArray.put(jo);
            });
            jsonObject.put("rows",jsonArray );
        }
//        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }
    /**
     * @Description 远端读取DCS数据，配置【修改】
     * @Author jiangyong xia
     * @Date 9:41 18/9/6
     * @Param
     * @return
     **/
    @RequestMapping(value="remoteUpdateGetDataIndex",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String remoteUpdateGetDataIndex(DevicePointPojo devicePointPojo){
        JSONObject jsonObject = new JSONObject();

        dcsService.saveDevicePointWithMainAttr(devicePointPojo);

        jsonObject.put("data","success");
        return jsonObject.toString();
    }
    /**
     * @Description 远端发送DCS数据，配置【查询】
     * @Author jiangyong xia
     * @Date 9:41 18/9/6
     * @Param
     * @return
     **/
    @RequestMapping(value="remoteSendDataIndex",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String remoteSendDataIndex(){
        List<DcsRemotePointPojo> dcsRemotePointPojoList = dcsRemotePointRepository.findAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",dcsRemotePointPojoList.size());
        JSONArray jsonArray = new JSONArray();
        dcsRemotePointPojoList.forEach((p)->{
            JSONObject jsonObjectTemp = new JSONObject();
            jsonObjectTemp.put("dcsRemotePointId",p.getDcsRemotePointId());
            jsonObjectTemp.put("remotePointName",p.getRemotePointName());
            jsonObjectTemp.put("dcsId",p.getDeviceDcsPojo().getDeviceId());
            jsonObjectTemp.put("address",p.getAddress());
            jsonObjectTemp.put("currentDate",p.getCurrTime().toLocalDateTime().toLocalDate());
            jsonObjectTemp.put("currentTime",p.getCurrTime().toLocalDateTime().toLocalTime());
            jsonObjectTemp.put("currentValue",p.getCurrentValue());
            jsonObjectTemp.put("note",p.getNote()==null?"":p.getNote());
            jsonObjectTemp.put("pipeId",p.getCoalPipingEntity().getId());
            jsonObjectTemp.put("densityOrVelocity",p.getDensityOrVelocity());
            jsonObjectTemp.put("slaveId",p.getSlaveId());

//            jsonObjectTemp.put("devcieName",p.getDevice)
            jsonArray.put(jsonObjectTemp);
        });
        jsonObject.put("rows",jsonArray);
        return jsonObject.toString();
    }
    /**
     * @Description 远端发送DCS数据，配置【修改】
     * @Author jiangyong xia
     * @Date 9:42 18/9/6
     * @Param
     * @return
     **/
    @RequestMapping(value="remoteUpdateSendDataIndex",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String remoteUpdateSendDataIndex(DcsRemotePointPojo dcsRemotePointPojo){
        JSONObject jsonObject = new JSONObject();

        dcsService.saveDevicePointWithMainAttr(dcsRemotePointPojo);

        jsonObject.put("data","success");
        return jsonObject.toString();
    }
}

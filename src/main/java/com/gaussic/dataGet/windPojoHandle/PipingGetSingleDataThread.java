package com.gaussic.dataGet.windPojoHandle;

import com.gaussic.model.CoalPipingEntity;
import com.gaussic.util.HandleDeviceData;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PipingGetSingleDataThread extends Thread {

    private CoalPipingEntity coalPipingEntity;
    private Date now;

    public PipingGetSingleDataThread() { }
    public PipingGetSingleDataThread(CoalPipingEntity coalPipingEntity, Date now) {
        this.coalPipingEntity = coalPipingEntity;
        this.now = now;
    }

    public void run() { updateData("url"); }

    /**
     * 更新数据,，返回需要更新的对象【已经更新保存到数据库】
     * 更新coalPipingEntity实时数据
     */
    public void updateData(final String url) {
//        CoalPipingSetEntity setEntity = coalPipingEntity.getCoalPipingSetEntity();
//        String url = coalPipingEntity.getCoalPipingSetEntity().getsUrl();
        // 直接读取数据库中的存储的URL地址
//        String url = "http://192.168.1.43:8080/windJsonProject/json.jsp";
        //01、获取设备的jsonp数据，返回一个JsonArray对象
        //TODO 关键处理方法
        /**************关键处理方法***********************/
        JSONArray jo = HandleDeviceData.httpRequest(url, "GET");
        //02、jsonArray对象转换为 中间对象“windDataPojo"
        //错误信息录入到Windpojo里面
        WindDataPojo windDataPojo = new WindDataPojo(jo);
        //03、更新 coalPipingEntity对象的风速与密度
        coalPipingEntity = WindPojoToEntityUtil.updateRealData(now.getTime(),coalPipingEntity,windDataPojo);

    }


    public CoalPipingEntity getCoalPipingEntity() { return coalPipingEntity; }

    public void setCoalPipingEntity(CoalPipingEntity coalPipingEntity) { this.coalPipingEntity = coalPipingEntity; }

    public Date getNow() { return now; }

    public void setNow(Date now) { this.now = now; }
}

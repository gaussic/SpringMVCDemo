package com.gaussic.controller;

import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.dcs.DeviceDcsPojo;
import com.gaussic.repository.CoalMillRepository;
import com.gaussic.repository.DeviceDcsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CommunicationController
 * @Description 通讯控制类
 * @Author jiangyong xia
 * @Date 18/8/30 18:03
 * @Version 1.0
 */
@Controller
public class CommunicationController {
    @Autowired
    private CoalMillRepository coalMillRepository;
    @Autowired
    private DeviceDcsRepository deviceDcsRepository;
    @RequestMapping(value="getAllDeviceState",method= RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllDeviceState(){

        JSONObject jsonObject = new JSONObject();

        JSONObject jsonObjectMillA = generatorJsonObjectMill(coalMillRepository.findOne(1L));
        JSONObject jsonObjectMillB = generatorJsonObjectMill(coalMillRepository.findOne(2L));
        JSONObject jsonObjectMillC = generatorJsonObjectMill(coalMillRepository.findOne(3L));
        JSONObject jsonObjectMillD = generatorJsonObjectMill(coalMillRepository.findOne(4L));

        DeviceDcsPojo deviceDcsPojo = deviceDcsRepository.findOne(1L);
        JSONObject jsonObjectDCS = new JSONObject();
        jsonObjectDCS.put("id",deviceDcsPojo.getDeviceId());
        jsonObjectDCS.put("name",deviceDcsPojo.getDeviceName());
        jsonObjectDCS.put("state",deviceDcsPojo.getDeviceLinkState());

        jsonObject.put("dcs",jsonObjectDCS);
        jsonObject.put("millA",jsonObjectMillA);
        jsonObject.put("millB",jsonObjectMillB);
        jsonObject.put("millC",jsonObjectMillC);
        jsonObject.put("millD",jsonObjectMillD);

        return jsonObject.toString();
    }

    private JSONObject generatorJsonObjectMill(CoalMillEntity coalMillEntity){

        JSONObject jsonObjectMill = new JSONObject();
        jsonObjectMill.put("id",coalMillEntity.getId());
        jsonObjectMill.put("name",coalMillEntity.getcName());
        List<CoalPipingEntity> coalPipingEntityList = coalMillEntity.getCoalPipingEntityList();
        List<CoalPipingEntity> coalPipingEntityListSorted = coalPipingEntityList.stream().sorted(Comparator.comparing
                (CoalPipingEntity::getId)).collect(Collectors.toList());


        jsonObjectMill.put("pipeA",generatorJsonObjectPipe(coalPipingEntityListSorted.get(0)));
        jsonObjectMill.put("pipeB",generatorJsonObjectPipe(coalPipingEntityListSorted.get(1)));
        jsonObjectMill.put("pipeC",generatorJsonObjectPipe(coalPipingEntityListSorted.get(2)));
        jsonObjectMill.put("pipeD",generatorJsonObjectPipe(coalPipingEntityListSorted.get(3)));

        return jsonObjectMill;
    }
    private JSONObject generatorJsonObjectPipe(CoalPipingEntity coalPipingEntity) {
        JSONObject jsonObjectPipe = new JSONObject();
        jsonObjectPipe.put("id",coalPipingEntity.getId());
        jsonObjectPipe.put("name",coalPipingEntity.getpName());
        jsonObjectPipe.put("state",coalPipingEntity.getpCommunicationState());
        return jsonObjectPipe;
    }
}

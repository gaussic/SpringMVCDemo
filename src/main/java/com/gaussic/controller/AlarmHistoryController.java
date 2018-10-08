package com.gaussic.controller;

import com.gaussic.model.AlarmHistoryEntity;
import com.gaussic.repository.AlarmHistoryRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class AlarmHistoryController {
    @Autowired
    private AlarmHistoryRepository alarmHistoryRepository;
    /*userDefine

     */
    public void test(){

    }

    @RequestMapping(value = "getAlarmHistoryPaging", method=RequestMethod.GET,produces="text/html;" +
            "charset=UTF-8")
    @ResponseBody

    public String getAlarmHistoryPaging(@RequestParam(value = "limit", required = false) int limit, @RequestParam
            (value = "offset", required = false) int offset) {

//        List<AlarmHistoryEntity> alarmHistoryEntityList = alarmHistoryRepository.findAlarmHistoryByPage(limit,offset);
        Pageable pageable = new PageRequest(offset, limit);
        Page<AlarmHistoryEntity> alarmHistoryEntityPage = alarmHistoryRepository.findAll(pageable);
        long totalElements = alarmHistoryEntityPage.getTotalElements();
        long totalPages = alarmHistoryEntityPage.getTotalPages();
        List<AlarmHistoryEntity> list = alarmHistoryEntityPage.getContent();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", totalElements);
//        jsonObject.put("rows",totalPages);
        JSONArray jsonArray = new JSONArray();
        for (AlarmHistoryEntity a : list) {
            LocalDateTime localDateTime = a.getaAlarmTime().toLocalDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            LocalTime localTime = localDateTime.toLocalTime();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", a.getId());
            jsonObject1.put("alarmDate", localDate);
            jsonObject1.put("alarmTime", localTime);

            jsonObject1.put("alarmType",a.getAlarmTypeString());

            jsonObject1.put("AlarmDesc",a.getaAlarmNote());
            jsonObject1.put("coalMillName",a.getaAlarmMillName());
            jsonObject1.put("pipe",a.getaAlarmPipeString());
            jsonObject1.put("alarmState",a.getaAlarmStateString());
            if(null != a.getaAlarmState() && a.getaAlarmState() == 0 && null != a.getaReAlarmTime()) {
                LocalDateTime localDateTimeForReAlarm = a.getaReAlarmTime().toLocalDateTime();
                jsonObject1.put("reAlarmDate",localDateTimeForReAlarm.toLocalDate());
                jsonObject1.put("reAlarmTime",localDateTimeForReAlarm.toLocalTime());
            }

            jsonObject1.put("confirmOperator",a.getaConfirmString());
            if(null != a.getaConfirmFlag() && a.getaConfirmFlag() && null != a.getaConfirmTime()){
                LocalDateTime localDateTime1 = a.getaConfirmTime().toLocalDateTime();
                jsonObject1.put("confirmDate",localDateTime1.toLocalDate());
                jsonObject1.put("confirmTime",localDateTime1.toLocalTime());
            }
            jsonObject1.put("confirmOperator",a.getaAlarmManagerName());



            jsonArray.put(jsonObject1);

        }
        jsonObject.put("rows", jsonArray);
        return jsonObject.toString();
    }
}

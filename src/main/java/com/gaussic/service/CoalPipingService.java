package com.gaussic.service;

import com.gaussic.model.AlarmHistoryEntity;
import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.CoalPipingSetEntity;
import com.gaussic.repository.AlarmHistoryRepository;
import com.gaussic.repository.CoalMillPipingSetRepository;
import com.gaussic.repository.CoalMillRepository;
import com.gaussic.repository.CoalPipingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoalPipingService {

    @Autowired
    private CoalMillPipingSetRepository coalMillPipingSetRepository;
    @Autowired
    private CoalMillRepository coalMillRepository;
    @Autowired
    private CoalPipingRepository coalPipingRepository;
    @Autowired
    private AlarmHistoryRepository alarmHistoryRepository;

    /**
     * 查询所有的管道，并且关联pipingSet对象
     * @return
     */
    public List<CoalPipingEntity> findAll(){
        List<CoalPipingEntity> coalPipingEntityList = coalPipingRepository.findAll();
        List<CoalPipingSetEntity> coalPipingSetEntityList = coalMillPipingSetRepository.findAll();
        for(CoalPipingEntity coalPipingEntity:coalPipingEntityList){
            Long coalPipingId = coalPipingEntity.getId();
            for(CoalPipingSetEntity coalPipingSetEntity:coalPipingSetEntityList){
                Long coalPipingSetPipingId = coalPipingSetEntity.getCoalPipeId();
                if(coalPipingId.equals(coalPipingSetPipingId)){
                    coalPipingEntity.setCoalPipingSetEntity(coalPipingSetEntity);
                    break;
                }
            }
        }
        return coalPipingEntityList;
    }


    /**
     * @Description 设置coalpipingEntity对象的通讯中断告警对象
     * 遍历告警信息，将  ”粉管通讯中断”  的告警信息查询出来,并设置到coalPipingEntity中
     * 查询当前粉管是否存在通讯中断告警，并且是正在告警中的状态。设置完成后，coalPipingEntity将存在alarmHistoryEntity对象。
     * @Author jiangyong xia
     * @Date 10:36 18/9/3
     * @param coalMillEntityList
     **/
    public void handleCoalPipingWithAlarmForCommunication(List<CoalMillEntity> coalMillEntityList) {
        List<AlarmHistoryEntity> alarmHistoryEntityList = alarmHistoryRepository.findByAAlarmTypeAndAAlarmState(AlarmHistoryEntity.ALARM_TYPE_COMMUNICATION_PIPE,1);
        for(CoalMillEntity coalMillEntity:coalMillEntityList){
            for(CoalPipingEntity coalPipingEntity:coalMillEntity.getCoalPipingEntityList()){
                if(null != alarmHistoryEntityList){
                    for(AlarmHistoryEntity alarmHistoryEntity:alarmHistoryEntityList){
                        if(null != alarmHistoryEntity.getaAlarmPipeId()){
                            if(alarmHistoryEntity.getaAlarmPipeId().equals(coalPipingEntity.getId())){
                                coalPipingEntity.setAlarmHistoryEntity(alarmHistoryEntity);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}

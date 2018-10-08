package com.gaussic.service;

import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.repository.CoalMillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoalMillService {
    @Autowired
    private CoalMillRepository coalMillRepository;
    @Autowired
    private CoalPipingService coalPipingService;

    /**
     * 查询所有coalMill对象，并且已经关联了coalPinigping，coalPipingSet
     * @return
     */
    public List<CoalMillEntity> findAll(){
        List<CoalMillEntity> coalMillEntityList = coalMillRepository.findAll();

       /* List<CoalPipingEntity> coalPipingEntityList = coalPipingService.findAll();
        for(CoalPipingEntity coalPipingEntity:coalPipingEntityList) {
            Long coalPipingMillId = coalPipingEntity.getpCoalMillId();
            for (CoalMillEntity coalMillEntity : coalMillEntityList) {
                Long coalMillId = coalMillEntity.getId();
                if(coalPipingMillId.equals(coalMillId)){
                    coalMillEntity.addCoalPiping(coalPipingEntity);
                    coalPipingEntity.setCoalMillEntity(coalMillEntity);
                    break;
                }
            }
        }*/
        return coalMillEntityList;
    }
}

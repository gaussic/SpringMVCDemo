package com.gaussic.service;

import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.CoalPipingSetEntity;
import com.gaussic.repository.CoalMillPipingSetRepository;
import com.gaussic.repository.CoalMillRepository;
import com.gaussic.repository.CoalPipingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class SetService {
    @Autowired
    private CoalMillPipingSetRepository coalMillPipingSetRepository;
    @Autowired
    private CoalMillRepository coalMillRepository;
    @Autowired
    private CoalPipingRepository coalPipingRepository;
    @Autowired
    private CoalPipingService coalPipingService;


    public List<CoalMillEntity> findAllCoalMillEntity() {
        List<CoalMillEntity> coalMillEntityList = coalMillRepository.findAll();
//        coalMillEntityList.get(0).getCoalPipingEntityList();
//        System.out.println(coalMillEntityList.get(0).getCoalPipingEntityList().get(0));
//        List<CoalPipingEntity> coalPipingEntityList = coalPipingService.findAll();
//        for(CoalMillEntity coalMillEntity:coalMillEntityList   ){
//            List<CoalPipingEntity> coalPipingEntityListTemp = coalMillEntity.getCoalPipingEntityList();
//            Long coalMillEntityId = coalMillEntity.getId();
//            for(CoalPipingEntity coalPipingEntity:coalPipingEntityList){
//                Long coalPipingMillId = coalPipingEntity.getpCoalMillId();
//                if(coalMillEntityId.equals(coalPipingMillId)){
//                    coalPipingEntityListTemp.add(coalPipingEntity);
//                }
//            }
//        }


        return coalMillEntityList;
    }
}

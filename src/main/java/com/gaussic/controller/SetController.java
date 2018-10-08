package com.gaussic.controller;

import com.gaussic.model.CoalMillEntity;
import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.CoalPipingSetEntity;
import com.gaussic.repository.CoalMillPipingSetRepository;
import com.gaussic.repository.CoalMillRepository;
import com.gaussic.repository.CoalPipingRepository;
import com.gaussic.repository.CoalPipingSetRepository;
import com.gaussic.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Transactional
public class SetController {

    @Autowired
    private SetService setService;
    @Autowired
    private CoalPipingRepository coalPipingRepository;
    @Autowired
    private CoalPipingSetRepository coalPipingSetRepository;
    @Autowired
    private CoalMillRepository coalMillRepository;

    @RequestMapping(value = "set/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        List<CoalMillEntity> coalMillEntityList = setService.findAllCoalMillEntity();
        modelMap.put("coalMillList", coalMillEntityList);
        return "setPages/index";
    }
    @RequestMapping(value = "set/editPipingSet" , method=RequestMethod.GET)
    public String editPipingSet(@RequestParam(value="piping.id" ,required = true)Long pipingId, ModelMap
            modelMap){
        if(null != pipingId){
            CoalPipingEntity coalPipingEntity = new CoalPipingEntity();
            coalPipingEntity.setId(pipingId);
            CoalPipingSetEntity coalPipingSetEntity = coalPipingSetRepository.findByCoalPipingEntity(coalPipingEntity);
            if(null != coalPipingSetEntity){
                modelMap.put("coalPipingSet",coalPipingSetEntity);
            }
        }
        return "setPages/pipingSet";
    }
    @RequestMapping(value="set/updatePipingSet",method=RequestMethod.GET)
    public String updatePipingSet(@ModelAttribute CoalPipingSetEntity coalPipingSetEntity){
        coalPipingSetRepository.saveAndFlush(coalPipingSetEntity);
        CoalPipingEntity coalPipingEntity = coalPipingSetEntity.getCoalPipingEntity();
        if(coalPipingEntity != null) {
            if(coalPipingEntity.getId() != 0L) {
                CoalPipingEntity coalPipingEntityFromDb = coalPipingRepository.findOne(coalPipingEntity.getId());
                if(coalPipingEntityFromDb != null) {
                    coalPipingEntityFromDb.setpLocationUserDefined(coalPipingEntity.getpLocationUserDefined());
                    coalPipingEntityFromDb.setpNameUserDefined(coalPipingEntity.getpNameUserDefined());
                    coalPipingEntityFromDb.setpNumUserDefined(coalPipingEntity.getpNumUserDefined());
                    coalPipingRepository.saveAndFlush(coalPipingEntityFromDb);
                }
            }
            CoalMillEntity coalMillEntity = coalPipingEntity.getCoalMillEntity();
            if(null != coalMillEntity){
                if(coalMillEntity.getId() != 0L){
                    CoalMillEntity coalMillEntityForDb = coalMillRepository.findOne(coalMillEntity.getId());
                    if(coalMillEntityForDb != null){
                        coalMillEntityForDb.setcName(coalMillEntity.getcName());
                        coalMillRepository.saveAndFlush(coalMillEntityForDb);
                    }
                }
            }
        }
        return "redirect:index";
    }
}


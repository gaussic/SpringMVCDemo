package com.gaussic.repository;

import com.gaussic.model.CoalPipingEntity;
import com.gaussic.model.CoalPipingSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoalPipingSetRepository extends JpaRepository<CoalPipingSetEntity,Long>{
//    CoalPipingSetEntity findByCoalPipingId(Long coalPipingId);
    CoalPipingSetEntity findByCoalPipingEntity(CoalPipingEntity coalPipingEntity);
}

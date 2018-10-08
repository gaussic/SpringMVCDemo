package com.gaussic.repository;

import com.gaussic.model.CoalPipingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoalPipingRepository extends JpaRepository<CoalPipingEntity,Long> {

}

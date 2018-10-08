package com.gaussic.repository;

import com.gaussic.model.CoalPipingSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoalMillPipingSetRepository extends JpaRepository<CoalPipingSetEntity,Long> {
}

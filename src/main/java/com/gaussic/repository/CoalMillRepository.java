package com.gaussic.repository;

import com.gaussic.model.CoalMillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoalMillRepository extends JpaRepository<CoalMillEntity,Long> {
}

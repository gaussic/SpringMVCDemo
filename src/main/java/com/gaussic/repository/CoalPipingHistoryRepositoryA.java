package com.gaussic.repository;

import com.gaussic.model.history.AcoalPipingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CoalPipingHistoryRepositoryA extends JpaRepository<AcoalPipingHistoryEntity,Long>{



     List<AcoalPipingHistoryEntity> findByHTimeAfterOrderByHTimeAsc(Timestamp latestTime);

     List<AcoalPipingHistoryEntity> findByHTimeBetween(Timestamp beginTime,Timestamp endTime);
}

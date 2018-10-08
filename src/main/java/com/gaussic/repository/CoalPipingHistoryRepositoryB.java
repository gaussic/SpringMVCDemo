package com.gaussic.repository;

import com.gaussic.model.history.AcoalPipingHistoryEntity;
import com.gaussic.model.history.BcoalPipingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CoalPipingHistoryRepositoryB extends JpaRepository<BcoalPipingHistoryEntity,Long> {

    List<BcoalPipingHistoryEntity> findByHTimeBetween(Timestamp beginTime, Timestamp endTime);
    List<BcoalPipingHistoryEntity> findByHTimeAfterOrderByHTimeAsc(Timestamp latestTime);


}

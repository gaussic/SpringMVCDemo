package com.gaussic.repository;

import com.gaussic.model.history.AcoalPipingHistoryEntity;
import com.gaussic.model.history.BcoalPipingHistoryEntity;
import com.gaussic.model.history.CcoalPipingHistoryEntity;
import com.gaussic.model.history.DcoalPipingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CoalPipingHistoryRepositoryC extends JpaRepository<CcoalPipingHistoryEntity, Long> {

    List<CcoalPipingHistoryEntity> findByHTimeBetween(Timestamp beginTime, Timestamp endTime);
    List<CcoalPipingHistoryEntity> findByHTimeAfterOrderByHTimeAsc(Timestamp latestTime);
}

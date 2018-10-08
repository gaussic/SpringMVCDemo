package com.gaussic.repository;

import com.gaussic.model.history.AcoalPipingHistoryEntity;
import com.gaussic.model.history.BcoalPipingHistoryEntity;
import com.gaussic.model.history.DcoalPipingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CoalPipingHistoryRepositoryD extends JpaRepository<DcoalPipingHistoryEntity,Long> {

    List<DcoalPipingHistoryEntity> findByHTimeBetween(Timestamp beginTime, Timestamp endTime);

    List<DcoalPipingHistoryEntity> findByHTimeAfterOrderByHTimeAsc(Timestamp latestTime);
}

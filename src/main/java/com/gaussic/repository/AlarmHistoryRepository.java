package com.gaussic.repository;

import com.gaussic.model.AlarmHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  AlarmHistoryRepository extends JpaRepository< AlarmHistoryEntity,Long> {

//    List<AlarmHistoryEntity> findAlarmHistoryByPage(int limit, int offset);

    Page<AlarmHistoryEntity> findAll(Pageable pageable);
//    aAlarmType
    List<AlarmHistoryEntity> findByAAlarmType(int alarmTypeCommunicationPipe);

    List<AlarmHistoryEntity> findByAAlarmTypeAndAAlarmState(int alarmTypeCommunicationPipe, int alarmState);
}

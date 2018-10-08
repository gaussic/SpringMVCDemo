package com.gaussic.repository;

import com.gaussic.model.dcs.DevicePointHistory1Pojo;
import com.gaussic.model.dcs.DevicePointPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevicePointRepository extends JpaRepository<DevicePointPojo,Long> {
    List<DevicePointPojo> findByPointAddress(String s);
}

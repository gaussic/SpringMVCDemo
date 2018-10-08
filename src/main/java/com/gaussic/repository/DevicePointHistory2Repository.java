package com.gaussic.repository;

import com.gaussic.model.dcs.DevicePointHistory1Pojo;
import com.gaussic.model.dcs.DevicePointHistory2Pojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePointHistory2Repository extends JpaRepository<DevicePointHistory2Pojo,Long> {
}

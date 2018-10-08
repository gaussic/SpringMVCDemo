package com.gaussic.repository;

import com.gaussic.model.dcs.DevicePointHistory1Pojo;
import com.gaussic.model.dcs.DevicePointHistory4Pojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePointHistory4Repository extends JpaRepository<DevicePointHistory4Pojo,Long> {
}

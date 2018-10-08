package com.gaussic.repository;

import com.gaussic.model.dcs.DeviceDcsPojo;
import com.gaussic.model.dcs.DevicePointHistory1Pojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicePointHistory1Repository extends JpaRepository<DevicePointHistory1Pojo,Long> {
}

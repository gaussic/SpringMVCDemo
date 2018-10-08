package com.gaussic.repository;

import com.gaussic.model.dcs.DeviceDcsPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDcsRepository extends JpaRepository<DeviceDcsPojo,Long> {
    //TODO 地址限制为固定
    List<DeviceDcsPojo> findByDeviceAddress(String s);
}

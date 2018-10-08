package com.gaussic.repository;

import com.gaussic.model.WUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WUserRepository extends JpaRepository<WUserEntity,Long> {

}

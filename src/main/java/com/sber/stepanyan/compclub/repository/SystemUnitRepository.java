package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.entity.SystemUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUnitRepository extends JpaRepository<SystemUnit, Long> {

}

package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.entity.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Workstation, Long> {

}

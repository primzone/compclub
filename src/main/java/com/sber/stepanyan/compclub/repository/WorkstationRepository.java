package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.entity.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkstationRepository extends JpaRepository<Workstation, Long> {


    Optional<Workstation> findWorkstationByWorkstationNumber(Integer workstationNumber);
}

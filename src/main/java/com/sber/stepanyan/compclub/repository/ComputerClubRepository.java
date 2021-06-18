package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.entity.ComputerClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerClubRepository extends JpaRepository<ComputerClub, Long> {

}

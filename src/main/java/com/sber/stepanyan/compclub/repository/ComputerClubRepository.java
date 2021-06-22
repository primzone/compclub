package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.entity.ComputerClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputerClubRepository extends JpaRepository<ComputerClub, Long> {

    Optional<ComputerClub> findComputerClubByName(String string);

}

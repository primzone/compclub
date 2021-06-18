package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

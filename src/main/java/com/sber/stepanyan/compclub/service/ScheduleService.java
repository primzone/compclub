package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void checkSchedules(Schedule schedule, Schedule s) {

        if (s.getStart().getTime() > schedule.getStart().getTime() &&
                schedule.getStart().getTime() < s.getEnd().getTime()){
            throw new InvalidValuesException("Это время уже занято");
        }

        if (s.getStart().getTime() > schedule.getEnd().getTime() &&
                schedule.getEnd().getTime() < s.getEnd().getTime()){
            throw new InvalidValuesException("Это время уже занято");
        }

    }
}

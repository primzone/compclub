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

        if ((schedule.getStart().getTime()+1) > s.getStart().getTime() &&
                (schedule.getStart().getTime()+1) < s.getEnd().getTime()){
            throw new InvalidValuesException("Это время уже занято");
        }

        if ((schedule.getEnd().getTime()-1)  > s.getStart().getTime() &&
                (schedule.getEnd().getTime()-1) < s.getEnd().getTime()){
            throw new InvalidValuesException("Это время уже занято");
        }

    }
}

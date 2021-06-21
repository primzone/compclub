package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class MonitorController {

    final MonitorService monitorService;


    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }


    @GetMapping("/monitors")//получить все доступные мониторы
    public List<Monitor> getAllMonitors(){

        List<Monitor> monitorslist = monitorService.getAllMonitors();

        return monitorslist;
    }

    @GetMapping("/monitors/{id}")//получить монитор по id
    public Monitor getMonitorById(@PathVariable long id){

        return monitorService.getMonitorById(id);

    }


    @PostMapping("/monitors")//добавить монитор
    public long addMonitor(@RequestBody Monitor monitor){

        long l = monitorService.addMonitor(monitor);
        return l;
    }


    @DeleteMapping("/monitors/{id}")//удаление монитора по id
    public long deleteMonitor(@PathVariable long id){
        monitorService.deleteMonitorById(id);
        return id;
    }


    @PutMapping("/monitors")
    public Monitor updateMonitor(@RequestBody Monitor monitor){

        Monitor updatedMonitor =  monitorService.updateMonitor(monitor);

        return updatedMonitor;
    }



}

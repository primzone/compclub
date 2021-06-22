package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.MonitorDTO;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.service.MonitorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee")
public class MonitorController {

    final MonitorService monitorService;


    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }


    @GetMapping("/monitors")//получить все доступные мониторы
    public List<MonitorDTO> getAllMonitors(){

        List<Monitor> monitorslist = monitorService.getAllMonitors();

        List<MonitorDTO> monitorDTOList = new ArrayList<>();
        for (Monitor m : monitorslist) {
            monitorDTOList.add(new MonitorDTO(m));
        }
        return monitorDTOList;
    }

    @GetMapping("/monitors/{id}")//получить монитор по id
    public MonitorDTO getMonitorById(@PathVariable long id){

        Monitor monitor = monitorService.getMonitorById(id);
        return  new MonitorDTO(monitor);

    }


    @PostMapping("/monitors")//добавить монитор
    public long addMonitor(@RequestBody MonitorDTO monitorDTO){

        long l = monitorService.addMonitor(monitorDTO);
        return l;
    }


    @DeleteMapping("/monitors/{id}")//удаление монитора по id
    public long deleteMonitor(@PathVariable long id){
        monitorService.deleteMonitorById(id);
        return id;
    }


    @PutMapping("/monitors")//изменение монитора
    public MonitorDTO updateMonitor(@RequestBody MonitorDTO monitorDTO){

        Monitor updatedMonitor =  monitorService.updateMonitor(monitorDTO);

        return new MonitorDTO(updatedMonitor);
    }



}

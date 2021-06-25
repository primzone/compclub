package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.MonitorDTO.AddMonitorDTO;
import com.sber.stepanyan.compclub.DTO.MonitorDTO.MonitorResponseDTO;
import com.sber.stepanyan.compclub.DTO.MonitorDTO.UpdateMonitorDTO;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.service.MonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("employee")
public class MonitorController {

    final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/monitors")//получить все доступные мониторы
    @ApiOperation(value = "получить все мониторы")
    public List<MonitorResponseDTO> getAllMonitors(){

        return monitorService.getAllMonitors();
    }

    @GetMapping("/monitors/{id}")//получить монитор по id
    @ApiOperation(value = "получить монитор по id")
    public MonitorResponseDTO getMonitorById(@PathVariable @Min(1) Long id){

        return  monitorService.getMonitorById(id);
    }


    @PostMapping("/monitors")//добавить монитор
    @ApiOperation(value = "добавить монитор")
    public Long addMonitor(@Valid @RequestBody AddMonitorDTO addMonitorDTO){

        return monitorService.addMonitor(addMonitorDTO);
    }

    @PutMapping("/monitors")//изменение монитора
    @ApiOperation(value = "изменить монитор")
    public MonitorResponseDTO updateMonitor(@Valid @RequestBody UpdateMonitorDTO updateMonitorDTO){

        return monitorService.updateMonitor(updateMonitorDTO);
    }

    @DeleteMapping("/monitors/{id}")//удаление монитора по id
    @ApiOperation(value = "удалить монитор")
    public Long deleteMonitor(@PathVariable @Min(1) Long id){
        monitorService.deleteMonitorById(id);
        return id;
    }






}

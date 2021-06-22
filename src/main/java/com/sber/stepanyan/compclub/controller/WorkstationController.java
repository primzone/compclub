package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.WorkstationDTO.WorkstationDTO;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.service.WorkstationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employee")
public class WorkstationController {

    final WorkstationService workstationService;

    public WorkstationController(WorkstationService workstationService) {
        this.workstationService = workstationService;
    }


    @GetMapping("/workstations")//получить все доступные рабочие станции
    public List<Workstation> getAllWorkstations(){

        List<Workstation> workstations = workstationService.getAllWorkstations();

        return workstations;
    }


    @GetMapping("/workstations/{id}")//получить все доступные рабочие станцию по айди
    public Workstation getWorkstationById(@PathVariable long id){

        Workstation workstation = workstationService.getWorkstationById(id);
        return workstation;
    }

    @PostMapping("/workstations")//добавить рабочую станцию
    public long addWorkstation(@RequestBody WorkstationDTO workstationDTO){

        long l = workstationService.addWorkstation(workstationDTO);
        return l;

    }

    @PutMapping("/workstations")//изменить рабочую станцию
    public Workstation updateWorkstation(@RequestBody WorkstationDTO workstationDTO){

        Workstation workstation = workstationService.updateWorkstation(workstationDTO);
        return workstation;

    }

    @DeleteMapping("/workstations/{id}")//удалить рабочую станцию
    public long deleteWorkstation(@PathVariable long id){
        workstationService.deleteWorkstation(id);
        return id;
    }


}

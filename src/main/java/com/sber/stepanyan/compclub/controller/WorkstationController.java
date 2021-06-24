package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.WorkstationDTO.AddWorkstationDTO;
import com.sber.stepanyan.compclub.DTO.WorkstationDTO.UpdateWorkstationDTO;
import com.sber.stepanyan.compclub.DTO.WorkstationDTO.WorkstationResponseDTO;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.service.WorkstationService;
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
import java.util.List;
@Validated
@RestController
@RequestMapping("employee")
public class WorkstationController {

    final WorkstationService workstationService;

    public WorkstationController(WorkstationService workstationService) {
        this.workstationService = workstationService;
    }


    @GetMapping("/workstations")//получить все доступные рабочие станции
    public List<WorkstationResponseDTO> getAllWorkstations(){

        return workstationService.getAllWorkstations();
    }


    @GetMapping("/workstations/{id}")//получить рабочую станцию по айди
    public WorkstationResponseDTO getWorkstationById(@PathVariable @Min(1) Long id){

        return workstationService.getWorkstationById(id);
    }

    @PostMapping("/workstations")//добавить рабочую станцию
    public Long addWorkstation(@Valid @RequestBody AddWorkstationDTO addWorkstationDTO){

        return workstationService.addWorkstation(addWorkstationDTO);

    }

    @PutMapping("/workstations")//изменить рабочую станцию
    public WorkstationResponseDTO updateWorkstation(@Valid @RequestBody UpdateWorkstationDTO updateWorkstationDTO){

        return workstationService.updateWorkstation(updateWorkstationDTO);
    }

    @DeleteMapping("/workstations/{id}")//удалить рабочую станцию
    public long deleteWorkstation(@PathVariable Long id){
        workstationService.deleteWorkstation(id);
        return id;
    }


}

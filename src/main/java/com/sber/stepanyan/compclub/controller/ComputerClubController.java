package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.AddComputerClubDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.AddWorkstationToComputerClubDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.ComputerClubResponseDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.UpdateComputerClubDTO;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.service.ComputerClubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
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
import java.util.Set;

@Validated
@RestController
@RequestMapping("employee")
public class ComputerClubController {

    final ComputerClubService computerClubService;
    final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ComputerClubController(ComputerClubService computerClubService, KafkaTemplate<String, String> kafkaTemplate) {
        this.computerClubService = computerClubService;
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping("/computerclub")//получить все компьютерные клубы
    @ApiOperation(value = "Получить все компьютерные клубы")
    public List<ComputerClubResponseDTO> getAllComputerClub(){
        return computerClubService.getAllComputerClub();
    }

    @GetMapping("/computerclub/{id}")//получить компьютерный клуб по айди
    @ApiOperation(value = "получить компьютерный клуб по id")
    public ComputerClubResponseDTO getComputerClubById(@PathVariable @Min(value = 1, message = "минимальный id = 1") Long id){
        return computerClubService.getComputerClubById(id);
    }

    @PostMapping("/computerclub")//
    @ApiOperation(value = "добавить компьютерный клуб")
    public Long addComputerClub(@Valid @RequestBody AddComputerClubDTO addComputerClubDTO){
        return computerClubService.addComputerClub(addComputerClubDTO);
    }

    @PutMapping("/computerclub")//
    @ApiOperation(value = "изменить компьютерный клуб")
    public UpdateComputerClubDTO updateComputerClub(@Valid @RequestBody UpdateComputerClubDTO updateComputerClubDTO){

        return computerClubService.updateComputerClub(updateComputerClubDTO);
    }

    @DeleteMapping("/computerclub/{id}")
    @ApiOperation(value = "удалить компьютерный клуб")
    public long deleteComputerClubById(@PathVariable @Min(value = 1, message = "минимальный id = 1") long id){
        computerClubService.deleteComputerClubById(id);
        return id;

    }

    @PutMapping("/computerclub/addworkstation")
    @ApiOperation(value = "добавить workstation к компьютерному клубу")
    public ComputerClubResponseDTO addWorkstationToComputerClub(@Valid @RequestBody AddWorkstationToComputerClubDTO addWorkstationToComputerClubDTO){

        return computerClubService.addWorkstationToComputerClub(addWorkstationToComputerClubDTO);

    }

//    @GetMapping("/computerclub/workstations/{id}")//получить все доступные рабочие станции по клуба по айди
//    public Set<WorkstationResponseDTO> getWorkstationsByCompClubId(@PathVariable Long id){
//
//        return computerClubService.getWorkstationsByCompClubId(id);;
//    }


}

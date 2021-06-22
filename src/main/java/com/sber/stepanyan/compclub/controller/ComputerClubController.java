package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.ComputerClubDTO;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.service.ComputerClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
import java.util.Set;

@RestController
@RequestMapping("employee")
public class ComputerClubController {

    final ComputerClubService computerClubService;
    final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "Kafka_New";

    @Autowired
    public ComputerClubController(ComputerClubService computerClubService, KafkaTemplate<String, String> kafkaTemplate) {
        this.computerClubService = computerClubService;
        this.kafkaTemplate = kafkaTemplate;
    }

//    @GetMapping("/publish/{message}")
//    public String printHello(@PathVariable("message") final String message){
//
//        kafkaTemplate.send(TOPIC, message);
//        return "Successfully send to kafka";
//    }


    @GetMapping("/computerclub")//получить все компьютерные клубы
    public List<ComputerClubDTO> getAllComputerClub(){

        List<ComputerClub> computerClubList = computerClubService.getAllComputerClub();
        List<ComputerClubDTO> computerClubDTOList = new ArrayList<>();

        for (ComputerClub c : computerClubList) {
            computerClubDTOList.add(new ComputerClubDTO(c));
        }

        return computerClubDTOList;

    }

    @GetMapping("/computerclub/{id}")//получить компьютерный клуб по айди
    public ComputerClubDTO getComputerClubById(@PathVariable Long id){

        ComputerClub computerClub = computerClubService.getComputerClubById(id);
        return new ComputerClubDTO(computerClub);
    }



    @PostMapping("/computerclub")// добавить рабочую станцию клубу
    public long addComputerClub(@RequestBody ComputerClubDTO computerClubDTO){

        long computerClubId = computerClubService.addComputerClub(computerClubDTO);

        return computerClubId;

    }


    @PutMapping("/computerclub")// добавить рабочую станцию клубу
    public ComputerClubDTO updateComputerClub(@RequestBody ComputerClubDTO computerClubDTO){

        ComputerClub updatedComputerClub = computerClubService.updateComputerClub(computerClubDTO);

        return new ComputerClubDTO(updatedComputerClub);

    }

    @DeleteMapping("/computerclub/{id}")
    public long deleteComputerClubById(@PathVariable long id){

        computerClubService.deleteComputerClubById(id);

        return id;

    }


    @GetMapping("/computerclub/workstations/{id}")//получить все доступные рабочие станции по клуба по айди
    public Set<Workstation> getWorkstationsByCompClubId(@PathVariable long id){

        Set<Workstation> workstations = computerClubService.getWorkstationsByCompClubId(id);
        return workstations;
    }


}

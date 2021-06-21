package com.sber.stepanyan.compclub.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.responses.MyResponse;
import com.sber.stepanyan.compclub.service.ComputerClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

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
    public List<ComputerClub> getAllComputerClub(){
        List<ComputerClub> computerClubList = computerClubService.getAllComputerClub();
        return computerClubList;
    }

    @GetMapping("/computerclub/{id}")//получить компьютерный клуб по айди
    public ComputerClub getComputerClubById(@PathVariable long id){

        ComputerClub computerClub = computerClubService.getComputerClubById(id);
        return computerClub;
    }



    @PostMapping("/computerclub")// добавить рабочую станцию клубу
    public long addComputerClub(@RequestBody ComputerClub computerClub){

        long computerClubId = computerClubService.addComputerClub(computerClub);

        return computerClubId;

    }


    @PutMapping("/computerclub")// добавить рабочую станцию клубу
    public ComputerClub updateComputerClub(@RequestBody ComputerClub computerClub){

        ComputerClub updatedComputerClub = computerClubService.updateComputerClub(computerClub);
        return updatedComputerClub;

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

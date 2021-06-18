package com.sber.stepanyan.compclub.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.responses.MyResponse;
import com.sber.stepanyan.compclub.service.ComputerClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/publish/{message}")
    public String printHello(@PathVariable("message") final String message){

        kafkaTemplate.send(TOPIC, message);
        return "Successfully send to kafka";
    }


    @PostMapping("/computerclub")
    public MyResponse addComputerClub(@RequestBody ComputerClub computerClub){

        computerClubService.addComputerClub(computerClub);
        return MyResponse.getSuccesResponse();

    }

    @GetMapping("/computerclub")
    public List<ComputerClub> getAllComputerClub(){
        List<ComputerClub> computerClubList = computerClubService.getAllComputerClub();

        return computerClubList;

    }



}

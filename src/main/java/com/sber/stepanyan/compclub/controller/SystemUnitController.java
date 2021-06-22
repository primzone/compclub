package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.SystemUnitDTO;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.service.SystemUnitService;
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
public class SystemUnitController {

    final SystemUnitService systemUnitService;

    public SystemUnitController(SystemUnitService systemUnitService) {
        this.systemUnitService = systemUnitService;
    }

    @GetMapping("/systemunits")//получить все доступные мониторы
    public List<SystemUnitDTO> getAllSystemUnits(){

        List<SystemUnit> systemUnitlist =  systemUnitService.getAllSystemUnits();

        List<SystemUnitDTO> systemUnitDTOList = new ArrayList<>();
        for (SystemUnit s : systemUnitlist) {
            systemUnitDTOList.add(new SystemUnitDTO(s));
        }
        return systemUnitDTOList;
    }

    @GetMapping("/systemunits/{id}")//получить все доступные мониторы
    public SystemUnitDTO getSystemUnitById(@PathVariable long id){

        SystemUnit systemUnit =  systemUnitService.getSystemUnitById(id);
        return new SystemUnitDTO(systemUnit);
    }

    @PostMapping("/systemunits")
    public long addSystemUnit(@RequestBody SystemUnitDTO systemUnitDTO){

        long l = systemUnitService.addSystemUnit(systemUnitDTO);
        return l;

    }

    @PutMapping("/systemunits")
    public SystemUnitDTO updateSystemUnit(@RequestBody SystemUnitDTO systemUnitDTO){

        SystemUnit updatedSystemUnit = systemUnitService.updateSystemUnit(systemUnitDTO);

        return new SystemUnitDTO(updatedSystemUnit);

    }

    @DeleteMapping("/systemunits/{id}")
    public long deleteSystemUnitById(@PathVariable long id){

        systemUnitService.deleteSystemUnitById(id);
        return id;
    }



}

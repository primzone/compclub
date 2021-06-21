package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.service.SystemUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class SystemUnitController {

    final SystemUnitService systemUnitService;

    public SystemUnitController(SystemUnitService systemUnitService) {
        this.systemUnitService = systemUnitService;
    }

    @GetMapping("/systemunits")//получить все доступные мониторы
    public List<SystemUnit> getAllSystemUnits(){

        List<SystemUnit> systemUnits =  systemUnitService.getAllSystemUnits();
        return systemUnits;
    }

    @GetMapping("/systemunits/{id}")//получить все доступные мониторы
    public SystemUnit getSystemUnitById(@PathVariable long id){

        SystemUnit systemUnit =  systemUnitService.getSystemUnitById(id);
        return systemUnit;
    }

    @PostMapping("/systemunits")
    public long addSystemUnit(@RequestBody SystemUnit systemUnit){

        long l = systemUnitService.addSystemUnit(systemUnit);
        return l;

    }

    @PutMapping("/systemunits")
    public SystemUnit updateSystemUnit(@RequestBody SystemUnit systemUnit){

        SystemUnit updatedSystemUnit = systemUnitService.updateSystemUnit(systemUnit);
        return updatedSystemUnit;

    }

    @DeleteMapping("/systemunits/{id}")
    public long deleteSystemUnitById(@PathVariable long id){

        systemUnitService.deleteSystemUnitById(id);
        return id;
    }



}

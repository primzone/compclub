package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.SystemUnitResponseDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.UpdateSystemUnitDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.addSystemUnitDTO;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.service.SystemUnitService;
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
public class SystemUnitController {

    final SystemUnitService systemUnitService;

    public SystemUnitController(SystemUnitService systemUnitService) {
        this.systemUnitService = systemUnitService;
    }

    @GetMapping("/systemunits")
    @ApiOperation(value = "получить все системные блоки")
    public List<SystemUnitResponseDTO> getAllSystemUnits(){

        return systemUnitService.getAllSystemUnits();
    }

    @GetMapping("/systemunits/{id}")
    @ApiOperation(value = "получить системный блок по id")
    public SystemUnitResponseDTO getSystemUnitById(@PathVariable @Min(1) Long id){

        return systemUnitService.getSystemUnitById(id);
    }

    @PostMapping("/systemunits")
    @ApiOperation(value = "добавить системный блок")
    public Long addSystemUnit(@Valid @RequestBody addSystemUnitDTO systemUnitDTO){

        return systemUnitService.addSystemUnit(systemUnitDTO);
    }

    @PutMapping("/systemunits")
    @ApiOperation(value = "изменить системный блок")
    public SystemUnitResponseDTO updateSystemUnit(@Valid @RequestBody UpdateSystemUnitDTO updateSystemUnitDTO){

        return systemUnitService.updateSystemUnit(updateSystemUnitDTO);
    }

    @DeleteMapping("/systemunits/{id}")
    @ApiOperation(value = "удалить системный блок")
    public Long deleteSystemUnitById(@PathVariable @Min(1) Long id){

        systemUnitService.deleteSystemUnitById(id);
        return id;
    }



}

package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.WorkstationDTO;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkstationService {

    final ComputerClubService computerClubService;
    final MonitorService monitorService;
    final SystemUnitService systemUnitService;
    final WorkstationRepository workstationRepository;

    public WorkstationService(WorkstationRepository workstationRepository, ComputerClubService computerClubService, MonitorService monitorService, SystemUnitService systemUnitService) {
        this.workstationRepository = workstationRepository;
        this.computerClubService = computerClubService;
        this.monitorService = monitorService;
        this.systemUnitService = systemUnitService;
    }


    public List<Workstation> getAllWorkstations() {

        List<Workstation> workstations = workstationRepository.findAll();

        if (workstations.isEmpty())
            throw new EmptyDataException("Список рабочих станций пуст");
        return workstations;
    }

    public Workstation getWorkstationById(long id) {

        Optional<Workstation> workstationOptional = workstationRepository.findById(id);

        if (workstationOptional.isEmpty())
            throw new EmptyDataException("Не найдена рабочая станция с id = " + id);

        return workstationOptional.get();

    }

    public long addWorkstation(WorkstationDTO workstationDTO) {

        ComputerClub computerClub = computerClubService.getComputerClubById(workstationDTO.getComputerClubId());
        Monitor monitor = monitorService.getMonitorById(workstationDTO.getMonitorId());
        SystemUnit systemUnit = systemUnitService.getSystemUnitById(workstationDTO.getComputerClubId());

        Workstation workstation = new Workstation(
                workstationDTO.getWorkstationNumber(),
                computerClub,
                monitor,
                systemUnit
        );
        //добавить все проверки на валидацию (номер раб станции)

        Workstation addedWorkstation = workstationRepository.save(workstation);

        return addedWorkstation.getId();

    }



    public void deleteWorkstation(long id) {
        workstationRepository.deleteById(id);
    }

    public Workstation updateWorkstation(WorkstationDTO workstationDTO) {

        Workstation workstation = getWorkstationById(workstationDTO.getId());

        //ДОДЕЛАТЬ

        return workstation;

    }
}

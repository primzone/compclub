package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.WorkstationDTO.WorkstationDTO;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.WorkstationRepository;
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

        checkValuesForNull(workstationDTO);
        Workstation addedWorkstation = checkValuesForCorrectness(workstationDTO, new Workstation());

        workstationRepository.save(addedWorkstation);

        return addedWorkstation.getId();

    }

    private Workstation checkValuesForCorrectness(WorkstationDTO workstationDTO, Workstation workstation) {
        if (workstationDTO.getWorkstationNumber() != null){
            if (workstationDTO.getWorkstationNumber() > 0 ){
                if (workstationRepository.findWorkstationByWorkstationNumber(workstationDTO.getWorkstationNumber()).isPresent())
                        throw new InvalidValuesException("Workstation с таким номером уже существует");
                workstation.setWorkstationNumber(workstationDTO.getWorkstationNumber());
            } else throw new InvalidValuesException("Номер workstation должен быть больше 0");
        }

        if (workstationDTO.getComputerClubId() != null)
            workstation.setComputerClub(computerClubService.getComputerClubById(workstationDTO.getComputerClubId()));

        if (workstationDTO.getMonitorId() != null)
            workstation.setMonitor(monitorService.getMonitorById(workstationDTO.getMonitorId()));

        if (workstationDTO.getSystemUnitId() != null)
            workstation.setSystemUnit(systemUnitService.getSystemUnitById(workstationDTO.getComputerClubId()));

        return workstation;
    }

    private void checkValuesForNull(WorkstationDTO workstationDTO) {
        if (workstationDTO.getWorkstationNumber() == null)
            throw new EmptyDataException("не указан номер workstation");
        if (workstationDTO.getComputerClubId() == null)
            throw new EmptyDataException("не указан id компьютерного клуба");
        if (workstationDTO.getMonitorId() == null)
            throw new EmptyDataException("не указан id монитора");
        if (workstationDTO.getSystemUnitId() == null)
            throw new EmptyDataException("не указан id системного блока");
    }


    public void deleteWorkstation(long id) {
        workstationRepository.deleteById(id);
    }

    public Workstation updateWorkstation(WorkstationDTO workstationDTO) {

        if (workstationDTO.getId() == null) throw new EmptyDataException("не указан id workstation");
        Workstation workstation = getWorkstationById(workstationDTO.getId());

        Workstation updatedWorkstation = checkValuesForCorrectness(workstationDTO, workstation);

        //ДОДЕЛАТЬ
        workstationRepository.save(updatedWorkstation);

        return updatedWorkstation;

    }
}

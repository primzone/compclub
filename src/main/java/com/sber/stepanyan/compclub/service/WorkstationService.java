package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.WorkstationDTO.AddWorkstationDTO;
import com.sber.stepanyan.compclub.DTO.WorkstationDTO.UpdateWorkstationDTO;
import com.sber.stepanyan.compclub.DTO.WorkstationDTO.WorkstationResponseDTO;
import com.sber.stepanyan.compclub.entity.Order;
import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.kafka.KafkaProducerService;
import com.sber.stepanyan.compclub.repository.WorkstationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class WorkstationService {

    private static final Logger log = LoggerFactory.getLogger(WorkstationService.class);

    final KafkaProducerService kafkaProducerService;
    final ComputerClubService computerClubService;
    final MonitorService monitorService;
    final SystemUnitService systemUnitService;
    final WorkstationRepository workstationRepository;
    final ScheduleService scheduleService;

    public WorkstationService(WorkstationRepository workstationRepository, ComputerClubService computerClubService, MonitorService monitorService, SystemUnitService systemUnitService, ScheduleService scheduleService, KafkaProducerService kafkaProducerService) {
        this.workstationRepository = workstationRepository;
        this.computerClubService = computerClubService;
        this.monitorService = monitorService;
        this.systemUnitService = systemUnitService;
        this.scheduleService = scheduleService;
        this.kafkaProducerService = kafkaProducerService;
    }


    public List<WorkstationResponseDTO> getAllWorkstations() {

        List<Workstation> workstations = workstationRepository.findAll();
        if (workstations.isEmpty()){
            log.info("Список рабочих станций пуст");
            throw new EmptyDataException("Список Workstations пуст");
        }

        List<WorkstationResponseDTO> workstationResponseDTOList = new ArrayList<>();
        for (Workstation w : workstations) {
            workstationResponseDTOList.add(new WorkstationResponseDTO(w));
        }
        log.info("Вернуть все Workstations");
        return workstationResponseDTOList;
    }

    public WorkstationResponseDTO getWorkstationById(Long id) {

        return new WorkstationResponseDTO(findWorkstationById(id));
    }

    private Workstation findWorkstationById(Long id) {

        Optional<Workstation> workstationOptional = workstationRepository.findById(id);
        if (workstationOptional.isEmpty()){
            log.info("Не найден Workstation с id [{}]", id);
            throw new EmptyDataException("Не найдена рабочая станция с id = " + id);
        }
        log.info("Вернуть Workstation по id [{}]", id);
        return workstationOptional.get();
    }

    public Long addWorkstation(AddWorkstationDTO addWorkstationDTO) {

        Workstation addedWorkstation = checkValuesForCorrectness(addWorkstationDTO, new Workstation());
        workstationRepository.save(addedWorkstation);
        log.info("Workstation с id [{}] добавлена", addedWorkstation.getId());
        kafkaProducerService.produce(new WorkstationResponseDTO(addedWorkstation));
        return addedWorkstation.getId();
    }

    public WorkstationResponseDTO updateWorkstation(UpdateWorkstationDTO updateWorkstationDTO) {

        Workstation workstation = findWorkstationById(updateWorkstationDTO.getId());
        Workstation updatedWorkstation = checkValuesForCorrectness(updateWorkstationDTO, workstation);

        workstationRepository.save(updatedWorkstation);
        log.info("Workstation id [{}] изменена", updatedWorkstation.getId());
        kafkaProducerService.produce(new WorkstationResponseDTO(updatedWorkstation));
        return new WorkstationResponseDTO(updatedWorkstation);
    }



    public void deleteWorkstation(Long id) {
        workstationRepository.deleteById(id);
        log.info("Workstation с id [{}] удалена", id);
    }


    private Workstation checkValuesForCorrectness(AddWorkstationDTO addWorkstationDTO, Workstation workstation) {

        if (workstationRepository.findWorkstationByWorkstationNumber(addWorkstationDTO.getWorkstationNumber()).isPresent()){
            log.info("Workstation c номером [{}] уже существует", addWorkstationDTO.getWorkstationNumber());
            throw new InvalidValuesException("Workstation с таким номером уже существует");
        }

        workstation.setWorkstationNumber(addWorkstationDTO.getWorkstationNumber());
        workstation.setComputerClub(computerClubService.findComputerClubById(addWorkstationDTO.getComputerClubId()));
        workstation.setMonitor(monitorService.findMonitorById(addWorkstationDTO.getMonitorId()));
        workstation.setSystemUnit(systemUnitService.findSystemUnitById(addWorkstationDTO.getComputerClubId()));

        return workstation;
    }


    private Workstation checkValuesForCorrectness(UpdateWorkstationDTO updateWorkstationDTO, Workstation workstation) {

        if (updateWorkstationDTO.getWorkstationNumber() != null){
            if (workstationRepository.findWorkstationByWorkstationNumber(updateWorkstationDTO.getWorkstationNumber()).isPresent()){
                throw new InvalidValuesException("Workstation с таким номером уже существует");
            }
            workstation.setWorkstationNumber(updateWorkstationDTO.getWorkstationNumber());
        }
        if (updateWorkstationDTO.getComputerClubId() != null){
            workstation.setComputerClub(computerClubService.findComputerClubById(updateWorkstationDTO.getComputerClubId()));
        }
        if (updateWorkstationDTO.getMonitorId() != null){
            workstation.setMonitor(monitorService.findMonitorById(updateWorkstationDTO.getMonitorId()));
        }
        if (updateWorkstationDTO.getSystemUnitId() != null){
            workstation.setSystemUnit(systemUnitService.findSystemUnitById(updateWorkstationDTO.getComputerClubId()));
        }

        return workstation;
    }


    public Workstation findWorkstationByWorkstationNumber(Integer workstationNumber) {

        Optional<Workstation> workstationOptional = workstationRepository.findWorkstationByWorkstationNumber(workstationNumber);
        if (workstationOptional.isEmpty()){
            log.info("Workstation с номером [{}] не существует", workstationNumber);
            throw new EmptyDataException("Workstation с номером = " + workstationNumber + " не существует");
        }

        log.info("Вернуть Workstation с id [{}]", workstationOptional.get().getId());
        return workstationOptional.get();
    }

    //проверка расписания
    public void checkScheduleForWorkstation(Workstation workstation, Schedule schedule) {

        for (Schedule s : workstation.getSchedules()) {
            scheduleService.checkSchedules(schedule, s);
        }

    }


    public void addOrderToWorkstation(Workstation workstation, Order order) {

        if (workstation.getOrders() == null){
            workstation.setOrders(new HashSet<>());
        }
        workstation.getOrders().add(order);
        order.setWorkstation(workstation);

    }
}

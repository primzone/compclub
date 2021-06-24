package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.MonitorDTO.AddMonitorDTO;
import com.sber.stepanyan.compclub.DTO.MonitorDTO.MonitorResponseDTO;
import com.sber.stepanyan.compclub.DTO.MonitorDTO.UpdateMonitorDTO;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonitorService {

    final MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }


    public List<MonitorResponseDTO> getAllMonitors() {

        List<Monitor> monitorList = monitorRepository.findAll();
        if (monitorList.isEmpty()){
            throw new EmptyDataException("Список мониторов пуст");
        }

        List<MonitorResponseDTO> monitorResponseDTOList = new ArrayList<>();
        for (Monitor m : monitorList) {
            monitorResponseDTOList.add(new MonitorResponseDTO(m));
        }

        return monitorResponseDTOList;
    }


    public MonitorResponseDTO getMonitorById(Long id) {

        return new MonitorResponseDTO(findMonitorById(id));
    }

    public Monitor findMonitorById(Long monitorId) {
        Optional<Monitor> monitorOptional = monitorRepository.findById(monitorId);
        if (monitorOptional.isEmpty()){
            throw new EmptyDataException("Монитора с id = " + monitorId + " не существует");
        }

        return monitorOptional.get();
    }

    public long addMonitor(AddMonitorDTO addMonitorDTO) {

        Monitor monitor = checkValuesForCorrectness(addMonitorDTO, new Monitor());

        Monitor addedMonitor = monitorRepository.save(monitor);
        return addedMonitor.getId();

    }

    public MonitorResponseDTO updateMonitor(UpdateMonitorDTO updateMonitorDTO) {

        Optional<Monitor> monitorOptional = monitorRepository.findById(updateMonitorDTO.getId());
        if (monitorOptional.isEmpty()){
            throw new EmptyDataException("Не найден монитор по id = " + updateMonitorDTO.getId());
        }

        Monitor updatedMonitor = checkValuesForCorrectness(updateMonitorDTO, monitorOptional.get());
        monitorRepository.save(updatedMonitor);

        return new MonitorResponseDTO(updatedMonitor) ;
    }


    public void deleteMonitorById(long id) {
        monitorRepository.deleteById(id);
    }




    private Monitor checkValuesForCorrectness(AddMonitorDTO addMonitorDTO, Monitor monitor) {

            monitor.setBrand(addMonitorDTO.getBrand());
            monitor.setModel(addMonitorDTO.getModel());
            monitor.setRefershRate(addMonitorDTO.getRefershRate());
            monitor.setPricePerHour(addMonitorDTO.getPricePerHour());
            monitor.setResolution(addMonitorDTO.getResolution());

        return monitor;
    }


    private Monitor checkValuesForCorrectness(UpdateMonitorDTO updateMonitorDTO, Monitor monitor) {
        if (updateMonitorDTO.getBrand() != null){
            monitor.setBrand(updateMonitorDTO.getBrand());
        }
        if (updateMonitorDTO.getModel() != null){

            monitor.setModel(updateMonitorDTO.getModel());
        }
        if (updateMonitorDTO.getRefershRate() != null){
            monitor.setRefershRate(updateMonitorDTO.getRefershRate());
        }
        if (updateMonitorDTO.getPricePerHour() != null){
            monitor.setPricePerHour(updateMonitorDTO.getPricePerHour());
        }
        if (updateMonitorDTO.getResolution() != null){
            monitor.setResolution(updateMonitorDTO.getResolution());
        }

        return monitor;
    }


}

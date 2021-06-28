package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.MonitorDTO.AddMonitorDTO;
import com.sber.stepanyan.compclub.DTO.MonitorDTO.MonitorResponseDTO;
import com.sber.stepanyan.compclub.DTO.MonitorDTO.UpdateMonitorDTO;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.kafka.KafkaProducerService;
import com.sber.stepanyan.compclub.repository.MonitorRepository;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonitorService {

    private static final Logger log = LoggerFactory.getLogger(MonitorService.class);
    final KafkaProducerService kafkaProducerService;

    final MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository, KafkaProducerService kafkaProducerService) {
        this.monitorRepository = monitorRepository;
        this.kafkaProducerService = kafkaProducerService;
    }


    @Counted(value = "counted.get.monitors", description = "Счетчик получений всех мониторов")
    @Timed(value = "timed.get.monitors", description = "Время на получение всех мониторов")
    public List<MonitorResponseDTO> getAllMonitors() {

        List<Monitor> monitorList = monitorRepository.findAll();
        if (monitorList.isEmpty()){
            log.info("Список мониторов пуст");
            throw new EmptyDataException("Список мониторов пуст");
        }

        List<MonitorResponseDTO> monitorResponseDTOList = new ArrayList<>();
        for (Monitor m : monitorList) {
            monitorResponseDTOList.add(new MonitorResponseDTO(m));
        }

        log.info("Вернуть список всех мониторов");
        return monitorResponseDTOList;
    }

    @Counted(value = "counted.get.monitor", description = "Счетчик получений мониторов по id")
    @Timed(value = "timed.get.monitor", description = "Время на получение монитора")
    public MonitorResponseDTO getMonitorById(Long id) {

        log.info("Вернуть монитор с id [{}]", id);
        return new MonitorResponseDTO(findMonitorById(id));
    }

    public Monitor findMonitorById(Long monitorId) {
        Optional<Monitor> monitorOptional = monitorRepository.findById(monitorId);
        if (monitorOptional.isEmpty()){
            log.info("Монитора с id [{}] не существует", monitorId);
            throw new EmptyDataException("Монитора с id = " + monitorId + " не существует");
        }

        log.info("Вернуть монитор с id [{}]", monitorOptional.get().getId());
        return monitorOptional.get();
    }

    @Counted(value = "counted.add.monitor", description = "Счетчик добавлений мониторов")
    @Timed(value = "timed.add.monitor", description = "Время на добавление монитора")
    public long addMonitor(AddMonitorDTO addMonitorDTO) {

        Monitor monitor = checkValuesForCorrectness(addMonitorDTO, new Monitor());

        Monitor addedMonitor = monitorRepository.save(monitor);
        log.info("Монитор с id [{}] добавлен", addedMonitor.getId());
        kafkaProducerService.produce(new MonitorResponseDTO(monitor));
        return addedMonitor.getId();

    }

    @Counted(value = "counted.update.monitor", description = "Счетчик изменений мониторов")
    @Timed(value = "timed.update.monitor", description = "Время на изменение монитора")
    public MonitorResponseDTO updateMonitor(UpdateMonitorDTO updateMonitorDTO) {

        Optional<Monitor> monitorOptional = monitorRepository.findById(updateMonitorDTO.getId());
        if (monitorOptional.isEmpty()){
            log.info("Не найден монитор по id [{}]", updateMonitorDTO.getId());
            throw new EmptyDataException("Не найден монитор по id = " + updateMonitorDTO.getId());
        }

        Monitor updatedMonitor = checkValuesForCorrectness(updateMonitorDTO, monitorOptional.get());
        monitorRepository.save(updatedMonitor);
        log.info("Монитор с id [{}] обновлен", updatedMonitor.getId());
        kafkaProducerService.produce(new MonitorResponseDTO(updatedMonitor));
        return new MonitorResponseDTO(updatedMonitor) ;
    }

    @Counted(value = "counted.delete.monitor", description = "Счетчик удаланий мониторов")
    @Timed(value = "timed.delete.monitor", description = "Время на удаление монитора")
    public void deleteMonitorById(long id) {
        monitorRepository.deleteById(id);
        log.info("Монитор с id [{}] удален", id);
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

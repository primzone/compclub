package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.UpdateComputerClubDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.SystemUnitResponseDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.UpdateSystemUnitDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.addSystemUnitDTO;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.kafka.KafkaProducerService;
import com.sber.stepanyan.compclub.repository.SystemUnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SystemUnitService {

    private static final Logger log = LoggerFactory.getLogger(SystemUnitService.class);
    final SystemUnitRepository systemUnitRepository;

    final KafkaProducerService kafkaProducerService;

    public SystemUnitService(SystemUnitRepository systemUnitRepository, KafkaProducerService kafkaProducerService) {
        this.systemUnitRepository = systemUnitRepository;
        this.kafkaProducerService = kafkaProducerService;
    }


    public List<SystemUnitResponseDTO> getAllSystemUnits() {

        List<SystemUnit> systemUnits = systemUnitRepository.findAll();
        if (systemUnits.isEmpty()){
            log.info("Список системных блоков пуст");
            throw new EmptyDataException("Список системных блоков пуст");
        }

        List<SystemUnitResponseDTO> systemUnitResponseDTOList = new ArrayList<>();

        for (SystemUnit s : systemUnits) {
            systemUnitResponseDTOList.add(new SystemUnitResponseDTO(s));
        }

        log.info("Вернуть все системные блоки");
        return systemUnitResponseDTOList;

    }

    public SystemUnitResponseDTO getSystemUnitById(Long id) {
        log.info("Вернуть системный блок по id [{}]", id);
        return new SystemUnitResponseDTO(findSystemUnitById(id));
    }

    public SystemUnit findSystemUnitById(Long computerClubId) {
        Optional<SystemUnit> systemUnitOptional = systemUnitRepository.findById(computerClubId);
        if (systemUnitOptional.isEmpty()){
            log.info("Не найден системный блок с id [{}]", computerClubId);
            throw new EmptyDataException("Не найден системный блок с id = " + computerClubId);
        }
        log.info("Вернуть системный блок с id [{}]", systemUnitOptional.get().getId());
        return systemUnitOptional.get();

    }

    public Long addSystemUnit(addSystemUnitDTO addSystemUnitDTO) {

        SystemUnit systemUnit = checkValuesForCorrectness(addSystemUnitDTO, new SystemUnit());
        SystemUnit addedSystemUnit = systemUnitRepository.save(systemUnit);
        log.info("Системный блок с id [{}] добавлен", systemUnit.getId());
        kafkaProducerService.produce(new SystemUnitResponseDTO(addedSystemUnit));
        return addedSystemUnit.getId();

    }


    public SystemUnitResponseDTO updateSystemUnit(UpdateSystemUnitDTO updateSystemUnitDTO) {

        SystemUnit systemUnit = findSystemUnitById(updateSystemUnitDTO.getId());

        SystemUnit updatedSystemUnit = checkValuesForCorrectness(updateSystemUnitDTO, systemUnit);

        systemUnitRepository.save(updatedSystemUnit);
        log.info("Системный блок с id");
        kafkaProducerService.produce(new SystemUnitResponseDTO(updatedSystemUnit));
        return new SystemUnitResponseDTO(updatedSystemUnit);
    }

    public void deleteSystemUnitById(long id) {
        systemUnitRepository.deleteById(id);
    }

    private SystemUnit checkValuesForCorrectness(addSystemUnitDTO systemUnitDTO, SystemUnit systemUnit) {

            systemUnit.setCpu(systemUnitDTO.getCpu());
            systemUnit.setCraphicsCard(systemUnitDTO.getCraphicsCard());
            systemUnit.setRam(systemUnitDTO.getRam());
            systemUnit.setPricePerHour(systemUnitDTO.getPricePerHour());

        return systemUnit;
    }

    private SystemUnit checkValuesForCorrectness(UpdateSystemUnitDTO updateSystemUnitDTO, SystemUnit systemUnit) {

        if (updateSystemUnitDTO.getCpu() != null){
            systemUnit.setCpu(updateSystemUnitDTO.getCpu());
        }
        if (updateSystemUnitDTO.getCraphicsCard() != null){
            systemUnit.setCraphicsCard(updateSystemUnitDTO.getCraphicsCard());
        }
        if (updateSystemUnitDTO.getRam() != null){
            systemUnit.setRam(updateSystemUnitDTO.getRam());
        }
        if (updateSystemUnitDTO.getPricePerHour() != null){
            systemUnit.setPricePerHour(updateSystemUnitDTO.getPricePerHour());
        }


        return systemUnit;
    }



}

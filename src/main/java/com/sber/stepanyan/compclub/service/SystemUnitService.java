package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.UpdateComputerClubDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.SystemUnitResponseDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.UpdateSystemUnitDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.addSystemUnitDTO;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.entity.SystemUnitPower;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.SystemUnitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SystemUnitService {

    final SystemUnitRepository systemUnitRepository;

    public SystemUnitService(SystemUnitRepository systemUnitRepository) {
        this.systemUnitRepository = systemUnitRepository;
    }


    public List<SystemUnitResponseDTO> getAllSystemUnits() {

        List<SystemUnit> systemUnits = systemUnitRepository.findAll();
        if (systemUnits.isEmpty()){
            throw new EmptyDataException("Список системных блоков пуст");
        }

        List<SystemUnitResponseDTO> systemUnitResponseDTOList = new ArrayList<>();

        for (SystemUnit s : systemUnits) {
            systemUnitResponseDTOList.add(new SystemUnitResponseDTO(s));
        }


        return systemUnitResponseDTOList;

    }

    public SystemUnitResponseDTO getSystemUnitById(Long id) {
        return new SystemUnitResponseDTO(findSystemUnitById(id));
    }

    public SystemUnit findSystemUnitById(Long computerClubId) {
        Optional<SystemUnit> systemUnitOptional = systemUnitRepository.findById(computerClubId);
        if (systemUnitOptional.isEmpty()){
            throw new EmptyDataException("Не найден системный блок с id = " + computerClubId);
        }
        return systemUnitOptional.get();

    }

    public Long addSystemUnit(addSystemUnitDTO addSystemUnitDTO) {

        SystemUnit systemUnit = checkValuesForCorrectness(addSystemUnitDTO, new SystemUnit());
        SystemUnit addedSystemUnit = systemUnitRepository.save(systemUnit);

        return addedSystemUnit.getId();

    }


    public SystemUnitResponseDTO updateSystemUnit(UpdateSystemUnitDTO updateSystemUnitDTO) {

        Optional<SystemUnit> systemUnitOptional = systemUnitRepository.findById(updateSystemUnitDTO.getId());
        if (systemUnitOptional.isEmpty()){
            throw new EmptyDataException("Не найден системный блок с id = " + updateSystemUnitDTO.getId());
        }

        SystemUnit updatedSystemUnit = checkValuesForCorrectness(updateSystemUnitDTO, systemUnitOptional.get());

        systemUnitRepository.save(updatedSystemUnit);

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
            if (SystemUnitPower.contains(systemUnitDTO.getPower().name())){
                systemUnit.setPower(systemUnitDTO.getPower());
            }
            else throw new InvalidValuesException("Допустымые значения для Power " + Arrays.toString(SystemUnitPower.values()));

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
        if (updateSystemUnitDTO.getPower() != null){
            if (SystemUnitPower.contains(updateSystemUnitDTO.getPower().name())){
                systemUnit.setPower(updateSystemUnitDTO.getPower());
            }
            else throw new InvalidValuesException("Допустымые значения для Power " + Arrays.toString(SystemUnitPower.values()));
        }

        return systemUnit;
    }



}

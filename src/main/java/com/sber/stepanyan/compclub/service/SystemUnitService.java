package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.SystemUnitDTO;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.entity.SystemUnitPower;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.SystemUnitRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SystemUnitService {

    final SystemUnitRepository systemUnitRepository;

    public SystemUnitService(SystemUnitRepository systemUnitRepository) {
        this.systemUnitRepository = systemUnitRepository;
    }


    public List<SystemUnit> getAllSystemUnits() {

        List<SystemUnit> systemUnits = systemUnitRepository.findAll();
        if (systemUnits.isEmpty())
            throw new EmptyDataException("Список системных блоков пуст");

        return systemUnits;

    }

    public SystemUnit getSystemUnitById(long id) {

        Optional<SystemUnit> systemUnitOptional = systemUnitRepository.findById(id);
        if (systemUnitOptional.isEmpty())
            throw new EmptyDataException("Не найден системный блок с id = " + id);

        return systemUnitOptional.get();

    }

    public long addSystemUnit(SystemUnitDTO systemUnitDTO) {

        checkValuesForNull(systemUnitDTO);
        SystemUnit systemUnit = checkValuesForCorrectness(systemUnitDTO, new SystemUnit());

        SystemUnit addedSystemUnit = systemUnitRepository.save(systemUnit);

        return addedSystemUnit.getId();

    }

    private SystemUnit checkValuesForCorrectness(SystemUnitDTO systemUnitDTO, SystemUnit systemUnit) {

        if (systemUnitDTO.getCpu() != null){
            if (systemUnitDTO.getCpu().length() >= 3 && systemUnitDTO.getCpu().length() <= 20)
                systemUnit.setCpu(systemUnitDTO.getCpu());
            else throw new InvalidValuesException("Длина поля CPU должна быть от 3 до 20 символов");
        }

        if (systemUnitDTO.getCraphicsCard() != null){
            if (systemUnitDTO.getCraphicsCard().length() >= 3 && systemUnitDTO.getCraphicsCard().length() <= 20)
                systemUnit.setCraphicsCard(systemUnitDTO.getCraphicsCard());
            else throw new InvalidValuesException("Длина поля CraphicsCard должна быть от 3 до 20 символов");
        }

        if (systemUnitDTO.getRam() != null){
            if (systemUnitDTO.getRam() >= 2 && systemUnitDTO.getRam() <= 256)
                systemUnit.setRam(systemUnitDTO.getRam());
            else throw new InvalidValuesException("Ram должен быть от 2 до 256Гб");
        }

        if (systemUnitDTO.getPricePerHour() != null){
            if (systemUnitDTO.getPricePerHour() >= 100 && systemUnitDTO.getPricePerHour() <= 500)
                systemUnit.setPricePerHour(systemUnitDTO.getPricePerHour());
            else throw new InvalidValuesException("Стоимость часа системного блока должна быть от 100 до 500р");
        }
        if (systemUnitDTO.getPower() != null){

           // SystemUnitPower.valueOf(systemUnitDTO.getPower().name());

            if (SystemUnitPower.contains(systemUnitDTO.getPower().name())){
                systemUnit.setPower(systemUnitDTO.getPower());
            }
            else throw new InvalidValuesException("Допустымые значения для Power " + Arrays.toString(SystemUnitPower.values()));
        }

        return systemUnit;
    }

    private void checkValuesForNull(SystemUnitDTO systemUnitDTO) {
        if (systemUnitDTO.getCpu() == null)
            throw new EmptyDataException("Не указан процессор системного блока");
        else if (systemUnitDTO.getCraphicsCard() == null)
            throw new EmptyDataException("Не указана видеокарта системного блока");
        else if (systemUnitDTO.getPricePerHour() == null)
            throw new EmptyDataException("Не указана цена за час системного блока");
        else if (systemUnitDTO.getRam() == null)
            throw new EmptyDataException("Не указана оперативная память системного блока");
        else if (systemUnitDTO.getPower() == null)
            throw new EmptyDataException("Не указана мощность системного блока");
    }



    public SystemUnit updateSystemUnit(SystemUnitDTO systemUnitDTO) {

        if (systemUnitDTO.getId() == null) throw new EmptyDataException("не указан id системного блока");
        Optional<SystemUnit> systemUnitOptional = systemUnitRepository.findById(systemUnitDTO.getId());
        if (systemUnitOptional.isEmpty())
            throw new EmptyDataException("Не найден системный блок с id = " + systemUnitDTO.getId());

        SystemUnit updatedSystemUnit = checkValuesForCorrectness(systemUnitDTO, systemUnitOptional.get());

        systemUnitRepository.save(updatedSystemUnit);

        return updatedSystemUnit;
    }

    public void deleteSystemUnitById(long id) {
        systemUnitRepository.deleteById(id);
    }
}

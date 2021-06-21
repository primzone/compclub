package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.entity.SystemUnit;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.repository.SystemUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public long addSystemUnit(SystemUnit systemUnit) {

        checkValuesForSystemUnit(systemUnit);
        //нужны ли проверки?
        SystemUnit addedSystemUnit = systemUnitRepository.save(systemUnit);

        return addedSystemUnit.getId();

    }

    void checkValuesForSystemUnit(SystemUnit systemUnit){

        //дописать валидацию
        if (systemUnit.getCpu() == null)
            throw new EmptyDataException("Не указан процессор системного блока");
        else if (systemUnit.getCraphicsCard() == null)
            throw new EmptyDataException("Не указана видеокарта системного блока");
        else if (systemUnit.getPricePerHour() == 0.0)
            throw new EmptyDataException("Не указана цена за час системного блока");
        else if (systemUnit.getRam() == 0)
            throw new EmptyDataException("Не указана оперативная память системного блока");
        else if (systemUnit.getPower() == null)
            throw new EmptyDataException("Не указана мощность системного блока");

    }


    public SystemUnit updateSystemUnit(SystemUnit systemUnit) {


        Optional<SystemUnit> systemUnitOptional = systemUnitRepository.findById(systemUnit.getId());
        if (systemUnitOptional.isEmpty())
            throw new EmptyDataException("Не найден системный блок с id = " + systemUnit.getId());

        SystemUnit oldSystemUnit = systemUnitOptional.get();

        if (systemUnit.getCpu() != null && !systemUnit.getCpu().equals(oldSystemUnit.getCpu())){
            oldSystemUnit.setCpu(systemUnit.getCpu());
        }
        if (systemUnit.getCraphicsCard() != null && !systemUnit.getCraphicsCard().equals(oldSystemUnit.getCraphicsCard())){
            oldSystemUnit.setCraphicsCard(systemUnit.getCraphicsCard());
        }
        if (systemUnit.getPower() != null && !systemUnit.getPower().equals(oldSystemUnit.getPower())){
            oldSystemUnit.setPower(systemUnit.getPower());
        }
        if (systemUnit.getPricePerHour() != 0.0){
            oldSystemUnit.setPricePerHour(systemUnit.getPricePerHour());
        }
        if (systemUnit.getRam() != 0){
            oldSystemUnit.setRam(systemUnit.getRam());
        }



        SystemUnit updatedSystemUnit = systemUnitRepository.save(oldSystemUnit);

        return updatedSystemUnit;
    }

    public void deleteSystemUnitById(long id) {
        systemUnitRepository.deleteById(id);
    }
}

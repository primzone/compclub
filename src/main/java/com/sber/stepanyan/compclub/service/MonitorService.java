package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.MonitorDTO;
import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorService {

    final MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }


    public List<Monitor> getAllMonitors() {

        List<Monitor> monitorList = monitorRepository.findAll();
        if (monitorList.isEmpty())
            throw new EmptyDataException("Список мониторов пуст");

        return monitorList;
    }

    public long addMonitor(MonitorDTO monitorDTO) {

        checkValuesForNull(monitorDTO);
        Monitor monitor = checkValuesForCorrectness(monitorDTO, new Monitor());

        Monitor addedMonitor = monitorRepository.save(monitor);
        return addedMonitor.getId();

    }




    public void deleteMonitorById(long id) {
        monitorRepository.deleteById(id);
    }

    public Monitor getMonitorById(long id) {

        Optional<Monitor> monitor = monitorRepository.findById(id);
        if (monitor.isEmpty())
            throw new EmptyDataException("Монитора с id = " + id + " не существует");

        return monitor.get();

    }


    public Monitor updateMonitor(MonitorDTO monitorDTO) {

        if (monitorDTO.getId() == null) throw new EmptyDataException("не указан id монитора");

        Optional<Monitor> monitorOptional = monitorRepository.findById(monitorDTO.getId());

        if (monitorOptional.isEmpty())
            throw new EmptyDataException("Не найден монитор по id = " + monitorDTO.getId());

        Monitor updatedMonitor = checkValuesForCorrectness(monitorDTO, monitorOptional.get());
        monitorRepository.save(updatedMonitor);

        return updatedMonitor;
    }

    void checkValuesForNull(MonitorDTO monitorDTO){
        //как это лучше можно сделать?
        //save() выкидывает общую ошибку
        if (monitorDTO.getBrand() == null)
            throw new EmptyDataException("Не указан бренд монитора");
        else if (monitorDTO.getModel() == null)
            throw new EmptyDataException("Не указана модель монитора");
        else if (monitorDTO.getRefershRate() == null)
            throw new EmptyDataException("Не указана частота обновления монитора");
        else if (monitorDTO.getPricePerHour() == null)
            throw new EmptyDataException("Не указана стоимость за час монитора");
        else if (monitorDTO.getResolution() == null)
            throw new EmptyDataException("Не указано разрешение монитора");
    }

    private Monitor checkValuesForCorrectness(MonitorDTO monitorDTO, Monitor monitor) {
        if (monitorDTO.getBrand() != null){
            if (monitorDTO.getBrand().length() >= 2 || monitorDTO.getBrand().length() <= 20)
                monitor.setBrand(monitorDTO.getBrand());
            else throw new InvalidValuesException("Длина поля Бренд должна быть от 2 до 20 символов");
        }

        if (monitorDTO.getModel() != null){
            if (monitorDTO.getModel().length() >= 2 || monitorDTO.getModel().length() <= 30)
                monitor.setModel(monitorDTO.getModel());
            else throw new InvalidValuesException("Длина поля Модель должна быть от 2 до 30 символов");
        }

        if (monitorDTO.getRefershRate() != null){
            if (monitorDTO.getRefershRate() >= 10 || monitorDTO.getRefershRate() <= 300)
                monitor.setRefershRate(monitorDTO.getRefershRate());
            else throw new InvalidValuesException("Частота обновления монитора должна быть от 10 до 300 Hz");
        }

        if (monitorDTO.getPricePerHour() != null){
            if (monitorDTO.getPricePerHour() >= 100 || monitorDTO.getPricePerHour() <= 300)
                monitor.setPricePerHour(monitorDTO.getPricePerHour());
            else throw new InvalidValuesException("Стоимость за час монитора должна быть от 100 до 300р ");
        }

        if (monitorDTO.getResolution() != null){
            if (monitorDTO.getResolution().length() >= 6 || monitorDTO.getResolution().length() <= 30)
                monitor.setResolution(monitorDTO.getResolution());
            else throw new InvalidValuesException("Разрешение монитора должна быть от 6 до 30 символов ");
        }

        return monitor;
    }

    //    private void checkValuesForCorrectness(MonitorDTO monitorDTO) {
//        if (monitorDTO.getBrand() != null && (monitorDTO.getBrand().length() < 2 || monitorDTO.getBrand().length() > 20))
//            throw new InvalidValuesException("Длина поля Бренд должна быть от 2 до 20 символов");
//        if (monitorDTO.getModel() != null && (monitorDTO.getModel().length() < 2 || monitorDTO.getModel().length() > 30))
//            throw new InvalidValuesException("Длина поля Модель должна быть от 2 до 30 символов");
//        if (monitorDTO.getRefershRate() != null && (monitorDTO.getRefershRate() < 10 || monitorDTO.getRefershRate() > 300))
//            throw new InvalidValuesException("Частота обновления монитора должна быть от 10 до 300 Hz");
//        if (monitorDTO.getPricePerHour() != null && (monitorDTO.getPricePerHour() < 100 || monitorDTO.getPricePerHour() > 300))
//            throw new InvalidValuesException("Стоимость за час монитора должна быть от 100 до 300р ");
//        if (monitorDTO.getResolution() != null && (monitorDTO.getResolution().length() < 6 || monitorDTO.getResolution().length() > 30))
//            throw new InvalidValuesException("Разрешение монитора должна быть от 6 до 30 символов ");
//    }
}

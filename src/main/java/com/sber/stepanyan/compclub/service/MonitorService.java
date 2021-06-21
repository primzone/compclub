package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.entity.Monitor;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
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

    public long addMonitor(Monitor monitor) {

        checkValuesForMonitor(monitor);

        Monitor addedMonitor = monitorRepository.save(monitor);
        return addedMonitor.getId();

    }


    void checkValuesForMonitor(Monitor monitor){
        //как это лучше можно сделать?
        //save() выкидывает общую ошибку
        if (monitor.getBrand() == null)
            throw new EmptyDataException("Не указан бренд монитора");
        else if (monitor.getModel() == null)
            throw new EmptyDataException("Не указана модель монитора");
        else if (monitor.getRefershRate() == 0)
            throw new EmptyDataException("Не указана частота обновления монитора");
        else if (monitor.getPricePerHour() == 0)
            throw new EmptyDataException("Не указана стоимость за час монитора");
        else if (monitor.getResolution() == null)
            throw new EmptyDataException("Не указано разрешение монитора");
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


    public Monitor updateMonitor(Monitor monitor) {

        Optional<Monitor> monitorOptional = monitorRepository.findById(monitor.getId());

        if (monitorOptional.isEmpty())
            throw new EmptyDataException("Не найден монитор по id = " + monitor.getId());

        Monitor oldMonitor = monitorOptional.get();

        //нужна ли проверка данных? или просто указать все в ентити?

        if (monitor.getBrand() != null && !monitor.getBrand().equals(oldMonitor.getBrand())){
            oldMonitor.setBrand(monitor.getBrand());
        }
        if (monitor.getResolution() != null && !monitor.getResolution().equals(oldMonitor.getResolution())){
            oldMonitor.setResolution(monitor.getResolution());
        }
        if (monitor.getModel() != null && !monitor.getModel().equals(oldMonitor.getModel())){
            oldMonitor.setModel(monitor.getModel());
        }
        if (monitor.getPricePerHour() != 0.0){
            oldMonitor.setPricePerHour(monitor.getPricePerHour());
        }
        if (monitor.getRefershRate() != 0){
            oldMonitor.setRefershRate(monitor.getRefershRate());
        }

        monitorRepository.save(oldMonitor);

        return oldMonitor;
    }
}

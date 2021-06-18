package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.ComputerClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerClubService {
    final ComputerClubRepository computerClubRepository;

    @Autowired
    public ComputerClubService(ComputerClubRepository computerClubRepository) {
        this.computerClubRepository = computerClubRepository;
    }


    public void addComputerClub(ComputerClub computerClub) {

        //добавить все проверки
        if (computerClub.getAddress() == null || computerClub.getName() == null){
            throw new InvalidValuesException("Неверные адрес или имя");
        }
        computerClubRepository.save(computerClub);
    }


    public List<ComputerClub> getAllComputerClub() {
        List<ComputerClub> allComputerClubs = computerClubRepository.findAll();
        if (allComputerClubs.isEmpty()) throw new EmptyDataException("У клуба пока нет рабочих станций");
        return allComputerClubs;
    }
}

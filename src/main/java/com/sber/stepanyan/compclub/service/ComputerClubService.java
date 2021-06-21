package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.ComputerClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ComputerClubService {
    final ComputerClubRepository computerClubRepository;

    @Autowired
    public ComputerClubService(ComputerClubRepository computerClubRepository) {
        this.computerClubRepository = computerClubRepository;
    }


    public long addComputerClub(ComputerClub computerClub) {

        //добавить все проверки
        if (computerClub.getAddress() == null || computerClub.getName() == null){
            throw new InvalidValuesException("Неверные адрес или имя");
        }
        ComputerClub addedComputerClub = computerClubRepository.save(computerClub);

        return addedComputerClub.getId();
    }


    public List<ComputerClub> getAllComputerClub() {
        List<ComputerClub> allComputerClubs = computerClubRepository.findAll();
        if (allComputerClubs.isEmpty()) throw new EmptyDataException("Пока нет компьютерных клубов");
        return allComputerClubs;
    }

    public ComputerClub getComputerClubById(long id) {

        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(id);
        if (computerClubOptional.isEmpty())
            throw new EmptyDataException("Компьютерный клуб по id = " + id + " не существует");

        return computerClubOptional.get();

    }

    public ComputerClub updateComputerClub(ComputerClub computerClub) {

        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(computerClub.getId());
        if (computerClubOptional.isEmpty())
            throw new EmptyDataException("Не найден компьютерный клуб с id = " + computerClub.getId());


        ComputerClub oldComputerClub = computerClubOptional.get();

        if (computerClub.getAddress() != null){
            oldComputerClub.setAddress(computerClub.getAddress());
        }
        if (computerClub.getName() != null){
            oldComputerClub.setName(computerClub.getName());
        }

        //добавить проверки на валидацию перед сохранением
        ComputerClub updatedComputerClub = computerClubRepository.save(oldComputerClub);

        return updatedComputerClub;

    }

    public void deleteComputerClubById(long id) {

        computerClubRepository.deleteById(id);
    }

    public Set<Workstation> getWorkstationsByCompClubId(long id) {

        ComputerClub computerClub = getComputerClubById(id);

        Set<Workstation> workstations = computerClub.getWorkstations();
        if (workstations.isEmpty())
            throw new EmptyDataException("У компьютерного клуба с id = " + id + " нет рабочих станций");
        return workstations;
    }
}

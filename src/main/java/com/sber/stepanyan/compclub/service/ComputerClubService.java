package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.ComputerClubDTO;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Monitor;
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


    public long addComputerClub(ComputerClubDTO computerClubDTO) {

        checkValuesForNull(computerClubDTO);
        ComputerClub computerClub = checkValuesForCorrectness(computerClubDTO, new ComputerClub());

        ComputerClub addedComputerClub = computerClubRepository.save(computerClub);

        return addedComputerClub.getId();
    }

    private ComputerClub checkValuesForCorrectness(ComputerClubDTO computerClubDTO, ComputerClub computerClub) {
        if (computerClubDTO.getName() != null){
            if (computerClubDTO.getName().length() >= 2 && computerClubDTO.getName().length() <= 20){

                if (computerClubRepository.findComputerClubByName(computerClubDTO.getName()).isEmpty())
                    computerClub.setName(computerClubDTO.getName());
                else throw new InvalidValuesException("Компьютерный клуб с таким именем уже существует");
            }
            else throw new InvalidValuesException("В названии клуба должно быть от 2 до 20 символов");
        }
        if (computerClubDTO.getAddress() != null){
            if (computerClubDTO.getAddress().length() >= 2 && computerClubDTO.getAddress().length() <= 200)
                computerClub.setAddress(computerClubDTO.getAddress());
            else throw new InvalidValuesException("В названии клуба должно быть от 2 до 200 символов");
        }

        return computerClub;
    }

    private void checkValuesForNull(ComputerClubDTO computerClubDTO) {
        if (computerClubDTO.getAddress() == null)
            throw new EmptyDataException("не указан адрес компьютерного клуба");
        if (computerClubDTO.getName() == null)
            throw new EmptyDataException("не указано название компьютерного клуба");
    }


    public List<ComputerClub> getAllComputerClub() {
        List<ComputerClub> allComputerClubs = computerClubRepository.findAll();
        if (allComputerClubs.isEmpty()) throw new EmptyDataException("Пока нет компьютерных клубов");
        return allComputerClubs;
    }

    public ComputerClub getComputerClubById(Long id) {

        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(id);
        if (computerClubOptional.isEmpty())
            throw new EmptyDataException("Компьютерный клуб по id = " + id + " не существует");

        return computerClubOptional.get();

    }

    public ComputerClub updateComputerClub(ComputerClubDTO computerClubDTO) {


        if (computerClubDTO.getId() == null) throw new EmptyDataException("Не указан id комрьютерного клуба");
        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(computerClubDTO.getId());
        if (computerClubOptional.isEmpty())
            throw new EmptyDataException("Не найден компьютерный клуб с id = " + computerClubDTO.getId());


        ComputerClub updatedComputerClub = checkValuesForCorrectness(computerClubDTO, computerClubOptional.get());

        computerClubRepository.save(updatedComputerClub);

        return updatedComputerClub;

    }

    public void deleteComputerClubById(long id) {
        //удалить каскадно все workstation and orders? или запрет на удаление?
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

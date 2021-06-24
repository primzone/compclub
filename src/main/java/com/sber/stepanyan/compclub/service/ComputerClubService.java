package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.AddComputerClubDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.ComputerClubResponseDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.UpdateComputerClubDTO;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.ComputerClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public long addComputerClub(AddComputerClubDTO addComputerClubDTO) {

        ComputerClub computerClub = checkValuesForCorrectness(addComputerClubDTO, new ComputerClub());
        ComputerClub addedComputerClub = computerClubRepository.save(computerClub);

        return addedComputerClub.getId();
    }


    public List<ComputerClubResponseDTO> getAllComputerClub() {
        List<ComputerClub> allComputerClubs = computerClubRepository.findAll();
        if (allComputerClubs.isEmpty()){
            throw new EmptyDataException("Пока нет компьютерных клубов");
        }

        List<ComputerClubResponseDTO> computerClubResponseDTOList = new ArrayList<>();
        for (ComputerClub c : allComputerClubs) {
            computerClubResponseDTOList.add(new ComputerClubResponseDTO(c));
        }

        return computerClubResponseDTOList;
    }

    public ComputerClubResponseDTO getComputerClubById(Long id) {

        return new ComputerClubResponseDTO(findComputerClubById(id));

    }

    public ComputerClub findComputerClubById(Long computerClubId) {
        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(computerClubId);
        if (computerClubOptional.isEmpty()){
            throw new EmptyDataException("Компьютерный клуб по id = " + computerClubId + " не существует");
        }
        return computerClubOptional.get();
    }

    public UpdateComputerClubDTO updateComputerClub(UpdateComputerClubDTO updateComputerClubDTO) {

        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(updateComputerClubDTO.getId());
        if (computerClubOptional.isEmpty()){
            throw new EmptyDataException("Не найден компьютерный клуб с id = " + updateComputerClubDTO.getId());
        }

        ComputerClub updatedComputerClub = checkValuesForCorrectness(updateComputerClubDTO, computerClubOptional.get());
        computerClubRepository.save(updatedComputerClub);

        return new UpdateComputerClubDTO(updatedComputerClub);

    }

    private ComputerClub checkValuesForCorrectness(AddComputerClubDTO addComputerClubDTO, ComputerClub computerClub) {


        if (computerClubRepository.findComputerClubByName(addComputerClubDTO.getName()).isEmpty()){
            computerClub.setName(addComputerClubDTO.getName());
        }
        else throw new InvalidValuesException("Компьютерный клуб с таким именем уже существует");

        computerClub.setAddress(addComputerClubDTO.getAddress());

        return computerClub;
    }


    private ComputerClub checkValuesForCorrectness(UpdateComputerClubDTO updateComputerClubDTO, ComputerClub computerClub) {

        if (updateComputerClubDTO.getName() != null){
            if (computerClubRepository.findComputerClubByName(updateComputerClubDTO.getName()).isEmpty()){
                computerClub.setName(updateComputerClubDTO.getName());
            }
            else throw new InvalidValuesException("Компьютерный клуб с таким именем уже существует");
        }

        if (updateComputerClubDTO.getAddress() != null){
            computerClub.setAddress(updateComputerClubDTO.getAddress());
        }

        return computerClub;
    }


    public void deleteComputerClubById(long id) {
        //удалить каскадно все workstation and orders? или запрет на удаление?
        computerClubRepository.deleteById(id);
    }



//    public Set<Workstation> getWorkstationsByCompClubId(long id) {
//ПЕРЕДЕЛАТЬ
//        ComputerClub computerClub = getComputerClubById(id);
//        Set<Workstation> workstations = computerClub.getWorkstations();
//        if (workstations.isEmpty())
//            throw new EmptyDataException("У компьютерного клуба с id = " + id + " нет рабочих станций");
//        return workstations;
//    }
}

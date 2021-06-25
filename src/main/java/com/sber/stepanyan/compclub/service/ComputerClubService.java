package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.AddComputerClubDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.AddWorkstationToComputerClubDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.ComputerClubResponseDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.UpdateComputerClubDTO;
import com.sber.stepanyan.compclub.entity.ComputerClub;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.kafka.KafkaProducerService;
import com.sber.stepanyan.compclub.repository.ComputerClubRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ComputerClubService {


    private static final Logger log = LoggerFactory.getLogger(ComputerClubService.class);
    final KafkaProducerService kafkaProducerService;
    final ComputerClubRepository computerClubRepository;
    final WorkstationService workstationService;

    @Autowired
    public ComputerClubService(ComputerClubRepository computerClubRepository, KafkaProducerService kafkaProducerService, @Lazy WorkstationService workstationService) {
        this.computerClubRepository = computerClubRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.workstationService = workstationService;
    }


    public long addComputerClub(AddComputerClubDTO addComputerClubDTO) {

        ComputerClub computerClub = checkValuesForCorrectness(addComputerClubDTO, new ComputerClub());
        ComputerClub addedComputerClub = computerClubRepository.save(computerClub);


        kafkaProducerService.produce(new ComputerClubResponseDTO());
        log.info("Добавлен компьютерный клуб с id [{}]", addedComputerClub.getId());
        return addedComputerClub.getId();
    }


    public List<ComputerClubResponseDTO> getAllComputerClub() {
        List<ComputerClub> allComputerClubs = computerClubRepository.findAll();
        if (allComputerClubs.isEmpty()){
            log.info("Пока нет компьютерных клубов");
            throw new EmptyDataException("Пока нет компьютерных клубов");
        }

        List<ComputerClubResponseDTO> computerClubResponseDTOList = new ArrayList<>();
        for (ComputerClub c : allComputerClubs) {
            computerClubResponseDTOList.add(new ComputerClubResponseDTO(c));
        }
        log.info("Вернуть все компьютерные клубы");
        return computerClubResponseDTOList;
    }

    public ComputerClubResponseDTO getComputerClubById(Long id) {

        log.info("Получить компьютерный клуб по id [{}]", id);
        return new ComputerClubResponseDTO(findComputerClubById(id));

    }

    public ComputerClub findComputerClubById(Long computerClubId) {
        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(computerClubId);
        if (computerClubOptional.isEmpty()){
            log.info("Компьютерного клуба по id [{}] не существует", computerClubId);
            throw new EmptyDataException("Компьютерного клуба по id = " + computerClubId + " не существует");
        }
        log.info("Вернуть компьютерный клуб по id [{}]", computerClubOptional.get().getId());
        return computerClubOptional.get();
    }

    public UpdateComputerClubDTO updateComputerClub(UpdateComputerClubDTO updateComputerClubDTO) {

        Optional<ComputerClub> computerClubOptional = computerClubRepository.findById(updateComputerClubDTO.getId());
        if (computerClubOptional.isEmpty()){
            log.info("Не найден компьютерный клуб по id [{}]", updateComputerClubDTO.getId());
            throw new EmptyDataException("Не найден компьютерный клуб с id = " + updateComputerClubDTO.getId());
        }

        ComputerClub updatedComputerClub = checkValuesForCorrectness(updateComputerClubDTO, computerClubOptional.get());
        computerClubRepository.save(updatedComputerClub);
        log.info("Компьютерный клуб с id [{}] обновлен", updatedComputerClub.getId());

        kafkaProducerService.produce(new ComputerClubResponseDTO());
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

        computerClubRepository.deleteById(id);
        log.info("Компьютерный клуб с id [{}] удален", id);
    }


    public ComputerClubResponseDTO addWorkstationToComputerClub(AddWorkstationToComputerClubDTO addWorkstationToComputerClubDTO) {

        ComputerClub computerClub = findComputerClubById(addWorkstationToComputerClubDTO.getComputerClubId());
        Workstation workstation = workstationService.findWorkstationByWorkstationNumber(addWorkstationToComputerClubDTO.getWorkstationNumber());

        addWorkstationToComputerClub(computerClub, workstation);

        log.info("Workstation с номером [{}] добавлен в компьютерный клуб с id [{}]", addWorkstationToComputerClubDTO.getWorkstationNumber(), addWorkstationToComputerClubDTO.getWorkstationNumber());
        computerClubRepository.save(computerClub);

        return new ComputerClubResponseDTO(computerClub);
    }


    public void addWorkstationToComputerClub(ComputerClub computerClub, Workstation workstation){
        if (computerClub.getWorkstations() == null){
            computerClub.setWorkstations(new HashSet<>());

        }
        computerClub.getWorkstations().add(workstation);
        workstation.setComputerClub(computerClub);
    }//вывести в сервис
}

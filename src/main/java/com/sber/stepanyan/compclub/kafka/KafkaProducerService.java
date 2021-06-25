package com.sber.stepanyan.compclub.kafka;

import com.sber.stepanyan.compclub.DTO.AccountDTO.AccountResponseDTO;
import com.sber.stepanyan.compclub.DTO.ComputerClubDTO.ComputerClubResponseDTO;
import com.sber.stepanyan.compclub.DTO.MonitorDTO.MonitorResponseDTO;
import com.sber.stepanyan.compclub.DTO.OrderDTO.OrderResponseDTO;
import com.sber.stepanyan.compclub.DTO.SystemUnitDTO.SystemUnitResponseDTO;
import com.sber.stepanyan.compclub.DTO.WorkstationDTO.WorkstationResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    private static final String ACCOUNT_TOPIC = "Kafka_CompClub_Account";
    private static final String MONITOR_TOPIC = "Kafka_CompClub_Monitor";
    private static final String SYSTEMUNIT_TOPIC = "Kafka_CompClub_SystemUnit";
    private static final String ORDER_TOPIC = "Kafka_CompClub_Order";
    private static final String WORKSTATION_TOPIC = "Kafka_CompClub_Workstation";
    private static final String COMPUTERCLUB_TOPIC = "Kafka_CompClub_ComputerClub";

    private final KafkaTemplate<String, ComputerClubResponseDTO> computerClubResponseDTOKafkaTemplate;
    private final KafkaTemplate<String, MonitorResponseDTO> monitorResponseDTOKafkaTemplate;
    private final KafkaTemplate<String, OrderResponseDTO> orderResponseDTOKafkaTemplate;
    private final KafkaTemplate<String, SystemUnitResponseDTO> systemUnitResponseDTOKafkaTemplate;
    private final KafkaTemplate<String, WorkstationResponseDTO> workstationResponseDTOKafkaTemplate;
    private final KafkaTemplate<String, AccountResponseDTO> accountResponseDTOKafkaTemplate;



    public KafkaProducerService(KafkaTemplate<String, ComputerClubResponseDTO> computerClubResponseDTOKafkaTemplate,
                                KafkaTemplate<String, MonitorResponseDTO> monitorResponseDTOKafkaTemplate,
                                KafkaTemplate<String, OrderResponseDTO> orderResponseDTOKafkaTemplate,
                                KafkaTemplate<String, SystemUnitResponseDTO> stringSystemUnitResponseDTOKafkaTemplate,
                                KafkaTemplate<String, WorkstationResponseDTO> workstationResponseDTOKafkaTemplate, KafkaTemplate<String, AccountResponseDTO> accountResponseDTOKafkaTemplate) {
        this.computerClubResponseDTOKafkaTemplate = computerClubResponseDTOKafkaTemplate;
        this.monitorResponseDTOKafkaTemplate = monitorResponseDTOKafkaTemplate;
        this.orderResponseDTOKafkaTemplate = orderResponseDTOKafkaTemplate;
        this.systemUnitResponseDTOKafkaTemplate = stringSystemUnitResponseDTOKafkaTemplate;
        this.workstationResponseDTOKafkaTemplate = workstationResponseDTOKafkaTemplate;
        this.accountResponseDTOKafkaTemplate = accountResponseDTOKafkaTemplate;
    }

    public void produce(ComputerClubResponseDTO computerClubResponseDTO) {
        log.info("Producing to kafka the computerClubResponseDTO " + computerClubResponseDTO);
        computerClubResponseDTOKafkaTemplate.send(COMPUTERCLUB_TOPIC, computerClubResponseDTO);
    }


    public void produce(OrderResponseDTO orderResponseDTO) {
        log.info("Producing to kafka the orderResponseDTO " + orderResponseDTO);
        orderResponseDTOKafkaTemplate.send(ORDER_TOPIC, orderResponseDTO);
    }

    public void produce(MonitorResponseDTO monitorResponseDTO) {
        log.info("Producing to kafka the monitorResponseDTO " + monitorResponseDTO);
        monitorResponseDTOKafkaTemplate.send(MONITOR_TOPIC, monitorResponseDTO);
    }

    public void produce(SystemUnitResponseDTO systemUnitResponseDTO) {
        log.info("Producing to kafka the systemUnitResponseDTO " + systemUnitResponseDTO);
        systemUnitResponseDTOKafkaTemplate.send(SYSTEMUNIT_TOPIC, systemUnitResponseDTO);
    }

    public void produce(WorkstationResponseDTO workstationResponseDTO) {
        log.info("Producing to kafka the workstationResponseDTO " + workstationResponseDTO);
        workstationResponseDTOKafkaTemplate.send(WORKSTATION_TOPIC, workstationResponseDTO);
    }

    public void produce(AccountResponseDTO accountResponseDTO) {
        log.info("Producing to kafka the accountResponseDTO " + accountResponseDTO);
        accountResponseDTOKafkaTemplate.send(WORKSTATION_TOPIC, accountResponseDTO);
    }


}

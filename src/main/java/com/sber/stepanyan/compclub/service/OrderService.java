package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.OrderDTO.AddOrderDTO;
import com.sber.stepanyan.compclub.DTO.OrderDTO.ConfirmOrderDTO;
import com.sber.stepanyan.compclub.DTO.OrderDTO.OrderResponseDTO;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Order;
import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.kafka.KafkaProducerService;
import com.sber.stepanyan.compclub.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    final KafkaProducerService kafkaProducerService;

    final WorkstationService workstationService;
    final AccountService accountService;
    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, AccountService accountService, WorkstationService workstationService, KafkaProducerService kafkaProducerService) {
        this.orderRepository = orderRepository;
        this.accountService = accountService;
        this.workstationService = workstationService;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Set<OrderResponseDTO> getAllOrdersByAccountNumber(String accountNumber) {

        Account account = accountService.findAccountByAccountNUmber(accountNumber);

        Set<OrderResponseDTO> orderResponseDTOList = new HashSet<>();
        for (Order order : account.getOrders()) {
            orderResponseDTOList.add(new OrderResponseDTO(order));
        }

        log.info("Вернуть все заказы по номеру аккаунта [{}]", accountNumber);
        return orderResponseDTOList;
    }

    public OrderResponseDTO getOrderByOrderNumber(Long orderNumber) {

        log.info("Вернуть заказ по номеру [{}]", orderNumber);
        return new OrderResponseDTO(findOrderByOrderNumber(orderNumber));
    }

    public Order findOrderByOrderNumber(Long orderNumber) {

        Optional<Order> orderOptional = orderRepository.findByOrderNumber(orderNumber);
        if (orderOptional.isEmpty()) {
            log.info("Не найден заказ по номеру [{}]", orderNumber);
            throw new EmptyDataException("Не найден заказ по номеру = " + orderNumber);
        }

        log.info("Вернуть заказ по номеру [{}]", orderOptional.get().getId());
        return orderOptional.get();
    }

    public Long addOrder(AddOrderDTO addOrderDTO) {

        Order order = checkValuesForCorrectness(addOrderDTO, new Order());

        orderRepository.save(order);
        log.info("Заказ с id [{}] добавлен", order.getId());
        kafkaProducerService.produce(new OrderResponseDTO(order));
        return order.getId();
    }

    private Order checkValuesForCorrectness(AddOrderDTO addOrderDTO, Order order) {


        if (addOrderDTO.getEnd() - addOrderDTO.getStart() < 3600){
            throw new InvalidValuesException("Минимальный заказ workstation = 1 час");
        }

        Workstation workstation = workstationService.findWorkstationByWorkstationNumber(addOrderDTO.getWorkstationNumber());
        Account account = accountService.findAccountByAccountNUmber(addOrderDTO.getAccountNumber());
        Double count = workstation.getMonitor().getPricePerHour() + workstation.getSystemUnit().getPricePerHour();

        if (account.getBalance() < count){
            log.info("Недостаточно средств на счету [{}]", account.getAccountNumber());
            throw new InvalidValuesException("Недостаточно средств на счету = " + account.getAccountNumber());
        }

        order.setCount(count);
        order.setOrderNumber(findMaxOrderNumber() + 1);
        Schedule schedule = new Schedule(workstation, addOrderDTO.getStart(), addOrderDTO.getEnd());
        //проверка расписания
        workstationService.checkScheduleForWorkstation(workstation, schedule);

        //добавляем связи для сохранения
        addScheduleToOrder(order, schedule);
        accountService.addOrderToAccount(account, order);
        workstationService.addOrderToWorkstation(workstation, order);

        return order;

    }

    public void addScheduleToOrder(Order order, Schedule schedule){
        order.setSchedule(schedule);
        schedule.setOrder(order);
    }

    public Long findMaxOrderNumber(){
        Optional<Order> orderWithMaxNumberOptional = orderRepository.findFirstByOrderByOrderNumberDesc();
        if (orderWithMaxNumberOptional.isPresent()){
            return orderWithMaxNumberOptional.get().getOrderNumber();
        }
        else return (long) 0;
    };

    public OrderResponseDTO confirmOrder(ConfirmOrderDTO confirmOrderDTO) {

        Order order = findOrderByOrderNumber(confirmOrderDTO.getOrderNumber());
        if (order.getCount().compareTo(confirmOrderDTO.getCount()) == 0){
            order.setPaid(true);
        }
        else {
            log.info("Недостаточно средств для оплаты заказа на счету [{}]", order.getOrderNumber());
            throw new InvalidValuesException("Недостаточно средств для оплаты заказа");
        }
        orderRepository.save(order);
        log.info("Заказ с id [{}] оплачен", order.getId());
        kafkaProducerService.produce(new OrderResponseDTO(order));
        return new OrderResponseDTO(order);
    }




}

package com.sber.stepanyan.compclub.service;

import com.sber.stepanyan.compclub.DTO.OrderDTO.AddOrderDTO;
import com.sber.stepanyan.compclub.DTO.OrderDTO.OrderResponseDTO;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Order;
import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.entity.Workstation;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {


    final WorkstationService workstationService;
    final AccountService accountService;
    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, AccountService accountService, WorkstationService workstationService) {
        this.orderRepository = orderRepository;
        this.accountService = accountService;
        this.workstationService = workstationService;
    }

    public Set<OrderResponseDTO> getAllOrdersByAccountNumber(String accountNumber) {

        Account account = accountService.findAccountByAccountNUmber(accountNumber);

        Set<OrderResponseDTO> orderResponseDTOList = new HashSet<>();
        for (Order order : account.getOrders()) {
            orderResponseDTOList.add(new OrderResponseDTO(order));
        }

        return orderResponseDTOList;
    }

    public OrderResponseDTO getOrderByOrderNumber(Long orderNumber) {

        return new OrderResponseDTO(findOrderByOrderNumber(orderNumber));
    }

    public Order findOrderByOrderNumber(Long orderNumber) {

        Optional<Order> orderOptional = orderRepository.findByOrderNumber(orderNumber);
        if (orderOptional.isEmpty()) {
            throw new EmptyDataException("Не найден заказ по номеру = " + orderNumber);
        }

        return orderOptional.get();
    }

    public Long addOrder(AddOrderDTO addOrderDTO) {

        Order order = checkValuesForCorrectness(addOrderDTO, new Order());

        orderRepository.save(order);
        return order.getId();
    }

    private Order checkValuesForCorrectness(AddOrderDTO addOrderDTO, Order order) {


        if (addOrderDTO.getEnd().getTime() - addOrderDTO.getStart().getTime() < 3600){
            throw new InvalidValuesException("Минимальный заказ workstation = 1 час");
        }

        Workstation workstation = workstationService.findWorkstationByWorkstationNumber(addOrderDTO.getWorkstationNumber());
        Account account = accountService.findAccountByAccountNUmber(addOrderDTO.getAccountNumber());
        Double count = workstation.getMonitor().getPricePerHour() + workstation.getSystemUnit().getPricePerHour();

        if (account.getBalance() < count){
            throw new InvalidValuesException("Недостаточно средств на счету = " + account.getAccountNumber());
        }

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



}

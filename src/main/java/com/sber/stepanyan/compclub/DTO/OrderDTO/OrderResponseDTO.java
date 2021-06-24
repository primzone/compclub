package com.sber.stepanyan.compclub.DTO.OrderDTO;

import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Order;
import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.entity.Workstation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class OrderResponseDTO {


    private Long id;
    private Long orderNumber;
    private Double count;
    private Boolean paid;
    private String accountNumber;
    private Integer workstationNumber;
    private Schedule schedule;


    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.count = order.getCount();
        this.paid = order.getPaid();
        this.accountNumber = order.getAccount().getAccountNumber();
        this.workstationNumber = order.getWorkstation().getWorkstationNumber();
        this.schedule = order.getSchedule();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getWorkstationNumber() {
        return workstationNumber;
    }

    public void setWorkstationNumber(Integer workstationNumber) {
        this.workstationNumber = workstationNumber;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

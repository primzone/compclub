package com.sber.stepanyan.compclub.DTO.OrderDTO;

import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.entity.Workstation;

public class ConfirmOrderDTO {

    private Long id;
    private Long orderNumber;
    private Double count;
    private Boolean paid;
    private Account account;
    private Workstation workstation;
    private Schedule schedule;


}

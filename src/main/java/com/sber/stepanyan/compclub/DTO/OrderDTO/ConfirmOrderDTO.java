package com.sber.stepanyan.compclub.DTO.OrderDTO;

import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.entity.Workstation;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ConfirmOrderDTO {


    @Min(value = 1, message = "минимальный orderNumber = 1")
    private Long orderNumber;
    @Min(value = 100, message = "минимальный count = 1")
    private Double count;


    public ConfirmOrderDTO() {
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
}

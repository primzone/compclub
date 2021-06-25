package com.sber.stepanyan.compclub.DTO.OrderDTO;

import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Schedule;
import com.sber.stepanyan.compclub.entity.Workstation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

public class AddOrderDTO {

    @Pattern(regexp = "\\d{12}", message = "accountNumber должен состоять из 12 цифр")
    //@NotBlank(message = "accountNumber не должен быть пустым")
    private String accountNumber;
    @Min(value = 1, message = "минимальный workstationNumber = 1")
    @Max(value = Integer.MAX_VALUE, message = "максимальный workstationNumber = " + Integer.MAX_VALUE)
    //@NotEmpty(message = "workstationNumber не должен быть пустым")
    private Integer workstationNumber;

    private Long start;
    private Long end;


    public AddOrderDTO() {
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

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}

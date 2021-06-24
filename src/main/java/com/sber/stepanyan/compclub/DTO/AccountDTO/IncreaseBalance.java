package com.sber.stepanyan.compclub.DTO.AccountDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class IncreaseBalance {


    @Pattern(regexp = "\\d{12}", message = "accountNumber должен состоять из 12 цифр")
    @NotBlank(message = "accountNumber не должен быть пустым")
    private String accountNumber;
    @Min(value = 10, message = "минимальный payment = 10")
    @NotBlank(message = "payment не должен быть пустым")
    private Double payment;

    public IncreaseBalance() {
    }

    public IncreaseBalance(String accountNumber, double payment) {
        this.accountNumber = accountNumber;
        this.payment = payment;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }
}

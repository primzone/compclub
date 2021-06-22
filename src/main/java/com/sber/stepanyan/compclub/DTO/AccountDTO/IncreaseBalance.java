package com.sber.stepanyan.compclub.DTO.AccountDTO;

public class IncreaseBalance {

    private String accountNumber;

    private double payment;

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

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}

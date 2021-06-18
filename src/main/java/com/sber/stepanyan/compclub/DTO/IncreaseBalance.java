package com.sber.stepanyan.compclub.DTO;

public class IncreaseBalance {

    private String AccountNumber;

    private double payment;

    public IncreaseBalance() {
    }

    public IncreaseBalance(String accountNumber, double payment) {
        AccountNumber = accountNumber;
        this.payment = payment;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}

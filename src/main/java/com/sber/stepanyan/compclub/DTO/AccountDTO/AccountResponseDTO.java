package com.sber.stepanyan.compclub.DTO.AccountDTO;

import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Order;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

public class AccountResponseDTO {

    private String name;
    private String accountNumber;
    private Double balance;

    public AccountResponseDTO() {
    }

    public AccountResponseDTO(Account account) {
        this.name = account.getName();
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

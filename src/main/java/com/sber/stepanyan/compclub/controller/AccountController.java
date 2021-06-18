package com.sber.stepanyan.compclub.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sber.stepanyan.compclub.DTO.IncreaseBalance;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class AccountController {

    final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/account/{name}")//получить данные личного кабинета
    public Account getAccountByName(@PathVariable String name){

        return accountService.getAccountByName(name);
    }

    @PostMapping("/account")//добавить личный кабинет
    public long addAccount(@RequestBody Account account){

        long accountId = accountService.addAccount(account);
        return accountId;
    }

    @PutMapping("/account")//пополнение баланса личного кабинета
    public long increaseBalanceByAccountNumber(@RequestBody IncreaseBalance increaseBalance){

        long id = accountService.increaseBalanceByAccountNumber(increaseBalance);
        return id;

    }
    

}

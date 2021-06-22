package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.AccountDTO.AddAccountDTO;
import com.sber.stepanyan.compclub.DTO.AccountDTO.IncreaseBalance;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.service.AccountService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public long addAccount(@RequestBody AddAccountDTO addAccountDTO){

        long accountId = accountService.addAccount(addAccountDTO);
        return accountId;
    }

    @PutMapping ("/account")//пополнение баланса личного кабинета
    public long increaseBalanceByAccountNumber(@RequestBody IncreaseBalance increaseBalance){

        long id = accountService.increaseBalanceByAccountNumber(increaseBalance);
        return id;

    }

    @DeleteMapping("/account/{id}")
    public long deleteAccountById(@PathVariable Long id){
        accountService.deleteAccountById(id);
        return id;
    }


}

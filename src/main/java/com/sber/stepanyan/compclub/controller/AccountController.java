package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.AccountDTO.AddAccountDTO;
import com.sber.stepanyan.compclub.DTO.AccountDTO.IncreaseBalance;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.service.AccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("user")
@Validated
public class AccountController {

    final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{name}")//получить данные личного кабинета
    public Account getAccountByName(@PathVariable @Size(min = 3, max = 30, message = "name должен быть от 3 до 30 символов") String name){

        return accountService.getAccountByName(name);
    }

    @PostMapping("/account")//добавить личный кабинет
    public Long addAccount(@RequestBody @Valid AddAccountDTO addAccountDTO){

        return accountService.addAccount(addAccountDTO);

    }

    @PutMapping ("/account")//пополнение баланса личного кабинета
    public Long increaseBalanceByAccountNumber(@RequestBody @Valid IncreaseBalance increaseBalance){

        return accountService.increaseBalanceByAccountNumber(increaseBalance);

    }

    @DeleteMapping("/account/{id}")
    public Long deleteAccountById(@PathVariable Long id){
        accountService.deleteAccountById(id);
        return id;
    }


}

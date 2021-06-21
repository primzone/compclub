package com.sber.stepanyan.compclub.service;


import com.sber.stepanyan.compclub.DTO.IncreaseBalance;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.AccountRepository;
import com.sber.stepanyan.compclub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AccountService {

    final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountByName(String name) {

        Optional<Account> accountByName = accountRepository.findAccountByName(name);
        if (accountByName.isEmpty()) throw new EmptyDataException("Аккаунта с таким именем не существует");
        return accountByName.get();

    }

    public long addAccount(Account account) {

        Account newAccount;
        try {
            account.setAccountNumber(Utils.generateAccountNumber());
            newAccount = accountRepository.save(account);
        }catch (Exception e){
            e.printStackTrace();
            throw new InvalidValuesException("Не удалось добавить счет, проверьте правильность введеных данных");
        }

        return newAccount.getId();

    }

    public long increaseBalanceByAccountNumber(IncreaseBalance increaseBalance) {

        Optional<Account> accountByAccountNumber = accountRepository.findAccountByAccountNumber(increaseBalance.getAccountNumber());
        if (accountByAccountNumber.isEmpty()) throw new EmptyDataException(
                "Не найден личный кабинет по счету" + increaseBalance.getAccountNumber());

        Account account = accountByAccountNumber.get();
        account.setBalance(increaseBalance.getPayment() + account.getBalance());
        accountRepository.save(account);

        return account.getId();

    }


}

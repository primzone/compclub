package com.sber.stepanyan.compclub.service;


import com.sber.stepanyan.compclub.DTO.AccountDTO.AddAccountDTO;
import com.sber.stepanyan.compclub.DTO.AccountDTO.IncreaseBalance;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Order;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.AccountRepository;
import com.sber.stepanyan.compclub.utils.Utils;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;

@Service
public class AccountService {

    final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountByName(String name) {

        Optional<Account> accountByName = accountRepository.findAccountByName(name);
        if (accountByName.isEmpty()){
            throw new EmptyDataException("Аккаунта с таким именем не существует");
        }
        return accountByName.get();

    }

    public Long addAccount(AddAccountDTO addAccountDTO) {

        Account account = new Account(addAccountDTO.getName(), Utils.generateAccountNumber());
        checkAccountValidation(account);

        Account addedAccount = accountRepository.save(account);

        return addedAccount.getId();

    }

    public Long increaseBalanceByAccountNumber(IncreaseBalance increaseBalance) {

        Account account = findAccountByAccountNUmber(increaseBalance.getAccountNumber());
        account.setBalance(increaseBalance.getPayment() + account.getBalance());
        accountRepository.save(account);

        return account.getId();

    }

    void checkAccountValidation(Account account){

        if (accountRepository.findAccountByName(account.getName()).isPresent()){
            throw new InvalidValuesException("Личный аккаунт с таким именем уже существует");
        }

    }


    public void deleteAccountById(Long id) {

        accountRepository.deleteById(id);
    }

    public Account findAccountByAccountNUmber(String accountNumber) {

        Optional<Account> accountOptional = accountRepository.findAccountByAccountNumber(accountNumber);
        if (accountOptional.isEmpty()){
            throw new EmptyDataException("Не найден личный кабинет по счету" + accountNumber);
        }

        return accountOptional.get();
    }


    public void addOrderToAccount(Account account, Order order){

        if (account.getOrders() == null){
            account.setOrders(new HashSet<>());
        }

        account.getOrders().add(order);
        order.setAccount(account);

    }
}

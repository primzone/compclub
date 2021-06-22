package com.sber.stepanyan.compclub.service;


import com.sber.stepanyan.compclub.DTO.AccountDTO.AddAccountDTO;
import com.sber.stepanyan.compclub.DTO.AccountDTO.IncreaseBalance;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.repository.AccountRepository;
import com.sber.stepanyan.compclub.utils.Utils;
import org.springframework.stereotype.Service;


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

    public long addAccount(AddAccountDTO addAccountDTO) {

        Account account = new Account(addAccountDTO.getName(), Utils.generateAccountNumber());
        checkAccountValidation(account);

        Account addedAccount = accountRepository.save(account);

        return addedAccount.getId();

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

    void checkAccountValidation(Account account){

        if (accountRepository.findAccountByName(account.getName()).isPresent())
            throw new InvalidValuesException("Личный аккаунт с таким именем уже существует");
        else if (account.getName().length() < 3 && account.getName().length() > 30)
            throw new InvalidValuesException("Длина имени должна быть в промежутке от 3 до 30 символов");
        else if (accountRepository.findAccountByAccountNumber(account.getAccountNumber()).isPresent()){
            //случайно сгенерился одинаковый личный счет, нужно снова пересоздать
            addAccount(new AddAccountDTO(account.getName()));
        }
    }


    public void deleteAccountById(Long id) {

        accountRepository.deleteById(id);
    }
}

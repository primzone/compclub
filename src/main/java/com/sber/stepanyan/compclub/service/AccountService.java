package com.sber.stepanyan.compclub.service;


import com.sber.stepanyan.compclub.DTO.AccountDTO.AccountResponseDTO;
import com.sber.stepanyan.compclub.DTO.AccountDTO.AddAccountDTO;
import com.sber.stepanyan.compclub.DTO.AccountDTO.IncreaseBalance;
import com.sber.stepanyan.compclub.entity.Account;
import com.sber.stepanyan.compclub.entity.Order;
import com.sber.stepanyan.compclub.exception_handling.EmptyDataException;
import com.sber.stepanyan.compclub.exception_handling.InvalidValuesException;
import com.sber.stepanyan.compclub.kafka.KafkaProducerService;
import com.sber.stepanyan.compclub.repository.AccountRepository;
import com.sber.stepanyan.compclub.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;

@Service
public class AccountService {


    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    final KafkaProducerService kafkaProducerService;
    final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository, KafkaProducerService kafkaProducerService) {
        this.accountRepository = accountRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public AccountResponseDTO getAccountByName(String name) {

        Optional<Account> accountByName = accountRepository.findAccountByName(name);
        if (accountByName.isEmpty()){
            log.info("Аккаунта с именем [{}] не существует", name);
            throw new EmptyDataException("Аккаунта с таким именем не существует");
        }

        log.info("Вернуть аккаунт с именем [{}]", name);
        return new AccountResponseDTO(accountByName.get());

    }

    public Long addAccount(AddAccountDTO addAccountDTO) {

        Account account = new Account(addAccountDTO.getName(), Utils.generateAccountNumber());
        checkAccountValidation(account);

        Account addedAccount = accountRepository.save(account);
        log.info("Добавить аккаунт с id [{}]", addedAccount.getId());
        kafkaProducerService.produce(new AccountResponseDTO(addedAccount));
        return addedAccount.getId();

    }

    public Long increaseBalanceByAccountNumber(IncreaseBalance increaseBalance) {

        Account account = findAccountByAccountNUmber(increaseBalance.getAccountNumber());
        account.setBalance(increaseBalance.getPayment() + account.getBalance());
        accountRepository.save(account);
        kafkaProducerService.produce(new AccountResponseDTO(account));
        log.info("Увеличен баланс аккаунта с id [{}] на [{}]", account.getId(), increaseBalance.getPayment());
        return account.getId();

    }

    void checkAccountValidation(Account account){

        if (accountRepository.findAccountByName(account.getName()).isPresent()){
            log.info("Аккаунт с именем [{}] уже существует", account.getName());
            throw new InvalidValuesException("Личный аккаунт с таким именем уже существует");
        }

    }


    public void deleteAccountById(Long id) {

        accountRepository.deleteById(id);
        log.info("Удален аккаунт с id [{}]", id);
    }

    public Account findAccountByAccountNUmber(String accountNumber) {

        Optional<Account> accountOptional = accountRepository.findAccountByAccountNumber(accountNumber);
        if (accountOptional.isEmpty()){
            log.info("Не найден личный кабинет по счету [{}]", accountNumber);
            throw new EmptyDataException("Не найден личный кабинет по счету" + accountNumber);
        }

        log.info("Вернуть аккаунт с номером [{}]", accountNumber);
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

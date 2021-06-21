package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.controller.AccountController;
import com.sber.stepanyan.compclub.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByName(String name);

    Optional<Account> findAccountByAccountNumber(String accountNumber);

}

package com.prowings.banking_application.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.banking_application.dao.AccountRepository;
import com.prowings.banking_application.models.Account;

@Service
public class AccountService {
	
	@Autowired
    private AccountRepository accountRepository;

    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account createAccount(String accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public Account deposit(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            return accountRepository.save(account);
        }
        return null;
    }

    public Account withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        if (account != null && account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            return accountRepository.save(account);
        }
        return null;
    }

}

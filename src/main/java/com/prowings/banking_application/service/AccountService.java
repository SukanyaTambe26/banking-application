package com.prowings.banking_application.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.prowings.banking_application.dao.AccountRepository;
import com.prowings.banking_application.models.Account;

@Service
public class AccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
    private AccountRepository accountRepository ;

    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public ResponseEntity<Account> createAccount(String accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);	
        //account.setBalance(BigDecimal.ZERO);
        
        Account savedAccount =  accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
       
    }
   @Transactional
   public ResponseEntity<Account> deposit(String accountNumber, BigDecimal amount) {
	   logger.info("Depositing amount: {} to accountNumber: {}", amount, accountNumber);
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            Account savedAccount =  accountRepository.save(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
        }
        logger.error("Account not found: {}", accountNumber);
        return null;
    }
   @Transactional
    public ResponseEntity<Account> withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        if (account != null && account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            Account savedAccount =  accountRepository.save(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
        }
        return null;
    }

}

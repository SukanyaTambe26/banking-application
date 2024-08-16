package com.prowings.banking_application.controller;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.banking_application.models.Account;
import com.prowings.banking_application.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam String accountNumber) {
        return accountService.createAccount(accountNumber);
        
    }
    
	/*
	 * @PostMapping("/create") public ResponseEntity<Account>
	 * createAccount(@RequestBody AccountRequest accountRequest) { if
	 * (accountRequest.getAccountNumber() == null ||
	 * accountRequest.getInitialDeposit() == null) { return
	 * ResponseEntity.badRequest().body("Missing required parameters."); } Account
	 * account = new Account();
	 * account.setAccountNumber(accountRequest.getAccountNumber());
	 * account.setBalance(accountRequest.getInitialDeposit()); 
	 * Account savedAccount= accountService.saveAccount(account); 
	 * return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount); }
	 */

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> checkBalance(@RequestParam String accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        if(account != null) {
        	return ResponseEntity.ok(account.getBalance());
            }
            return ResponseEntity.notFound().build();
        }
        
    }



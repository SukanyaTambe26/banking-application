package com.prowings.banking_application.controller;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Account createAccount(@RequestParam String accountNumber) {
        return accountService.createAccount(accountNumber);
    }

    @PostMapping("/deposit")
    public Account deposit(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    @GetMapping("/balance")
    public BigDecimal checkBalance(@RequestParam String accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        return account != null ? account.getBalance() : BigDecimal.ZERO;
    }

}

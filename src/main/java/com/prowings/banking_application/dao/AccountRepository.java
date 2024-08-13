package com.prowings.banking_application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prowings.banking_application.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	 Account findByAccountNumber(String accountNumber);

}

package com.hung.security.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hung.security.entity.AccountTransactions;
import com.hung.security.entity.Customer;
import com.hung.security.repository.AccountTransactionsRepository;

@RestController
public class BalanceRest {
	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;

	@PostMapping("/myBalance")
	public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
		List<AccountTransactions> accountTransactions = accountTransactionsRepository
				.findByCustomerIdOrderByTransactionDtDesc(customer.getId());
		if (accountTransactions != null) {
			return accountTransactions;
		} else {
			return null;
		}
	}
}

package com.bank.atm.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.atm.models.AccountModel;
import com.bank.atm.models.TransferLogModel;
import com.bank.atm.services.AccountService;

@RestController
@RequestMapping("/atm")
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public ArrayList<AccountModel> allAccounts(){
		return accountService.allAccounts();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<AccountModel> findIdAccount(@PathVariable("id") Long id){
		return this.accountService.findId(id);		
	}
				
	@PostMapping("/createBankAccount")
	public ResponseEntity<AccountModel> createAccount(@RequestBody AccountModel account) {
		
		if(account.getBalance() < 0 )
		{
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
			//return new ResponseEntity(HttpStatus.NOT_FOUND);
		}	
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.createAccount(account));
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<String> moneyTransfer(@RequestBody TransferLogModel log) {
		
		if(log.getAmount() <= 0 )
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The amount cannot be less than 0.1 Dollars");
		}
		
		if(log.getOriginAccount() <= 0 )
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The origin account must be a positive number ID");
		}
		
		if(log.getDestinationAccount() <= 0 )
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The destination account must be a positive number ID");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.saveTransaction(log));
	}
	
	@PostMapping("/transferLogs")
	public ArrayList<TransferLogModel> transactionRecord(@RequestBody long idAccount) {
		return this.accountService.transactionRecord(idAccount);
	}
	
	
}

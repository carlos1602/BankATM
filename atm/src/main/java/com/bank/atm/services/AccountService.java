package com.bank.atm.services;

import java.util.ArrayList;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.atm.models.AccountModel;
import com.bank.atm.models.TransferLogModel;
import com.bank.atm.repositories.AccountRepository;
import com.bank.atm.repositories.LogRepository;

@Service
@Transactional
public class AccountService {
	@Autowired
	LogRepository logRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	
	public ArrayList<AccountModel> allAccounts(){
		return (ArrayList<AccountModel>)accountRepository.findAll();
	}
	
	public AccountModel createAccount(AccountModel user){
		return accountRepository.save(user);
	}
	
	public Optional<AccountModel> findId(Long id){
		return accountRepository.findById(id);
	}
	
		
	public String saveTransaction(TransferLogModel log){
		
		try{			
			AccountModel findAccountOrigin = accountRepository.findByIdNumber(log.getOriginAccount());	
			
			double originUpdated = (double)findAccountOrigin.getBalance() - (double)log.getAmount();
			
			AccountModel findAccountDestination=accountRepository.findByIdNumber(log.getDestinationAccount());
				
			double destinationUpdated = (double)findAccountDestination.getBalance() +  (double)log.getAmount();												
								
			logRepository.save(log);
			
			findAccountOrigin.setBalance(originUpdated);
			accountRepository.save(findAccountOrigin);
			
			findAccountDestination.setBalance(destinationUpdated);
			accountRepository.save(findAccountDestination);
			
			
			return "Succesful Transaction";
			
		}catch(RuntimeException err){
			err.printStackTrace();
			return "Transaction denied. Please validate the input information: " + err.getMessage();
		}
		
	}
	
	public ArrayList<TransferLogModel> transactionRecord(long origin){
		return logRepository.findByOriginAccount(origin);
	}
	
	public boolean eliminarUsuario(Long id) {
		try{
			accountRepository.deleteById(id);
			return true;
		}catch(Exception err){
			return false;
		}
	}
}

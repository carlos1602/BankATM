package com.bank.atm;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.atm.models.AccountModel;
import com.bank.atm.models.TransferLogModel;
import com.bank.atm.services.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	
	@Autowired
	private AccountService accountService;
	
	
	
	@Test
	public void testSaveTransaction() {
		TransferLogModel transferLogModel = null;
		
		assertTrue(accountService.saveTransaction(transferLogModel) == "Transaction denied. Please validate the input information: null");
	}

}

package com.bank.atm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.atm.models.AccountModel;

@Repository
public interface AccountRepository extends CrudRepository<AccountModel, Long> {

	public abstract AccountModel findByIdNumber(long idNumber);
}



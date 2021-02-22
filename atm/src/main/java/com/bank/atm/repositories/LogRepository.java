package com.bank.atm.repositories;


import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bank.atm.models.TransferLogModel;

@Repository
public interface LogRepository extends CrudRepository<TransferLogModel, Long> {
	public abstract ArrayList<TransferLogModel> findByOriginAccount(long originAccount);
}


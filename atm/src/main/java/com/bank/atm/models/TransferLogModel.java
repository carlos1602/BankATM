package com.bank.atm.models;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name= "tb_logs")
public class TransferLogModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long idTransaction;
	
	@Column(nullable=false)
	private Long originAccount;
	
	public Long getOriginAccount() {
		return originAccount;
	}
	public void setOriginAccount(Long originAccount) {
		this.originAccount = originAccount;
	}
	public Long getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(Long destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	@Column(nullable=false)
	private Long destinationAccount;
	
	@Column(nullable=false)
	private Double amount;
	
	@Column(name="Date_Transaction", nullable=false)
	@CreationTimestamp
	private LocalDateTime dateTransaction;
	
	
	public LocalDateTime getDateTransaction() {
		return dateTransaction;
	}
	public void setDateTransaction(LocalDateTime dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
	public Long getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
	
}



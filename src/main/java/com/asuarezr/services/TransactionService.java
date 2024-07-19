package com.asuarezr.services;

import com.asuarezr.services.dtos.TransactionDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TransactionService {

  private final TransactionServiceRepository transactionServiceRepository;

  @Inject
  public TransactionService(TransactionServiceRepository transactionServiceRepository) {
    this.transactionServiceRepository = transactionServiceRepository;
  }

  public TransactionDto process(TransactionDto transaction){
      this.transactionServiceRepository.save(transaction);
      return transaction;
  }
}

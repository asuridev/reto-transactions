package com.asuarezr.services;

import com.asuarezr.services.dtos.TransactionDto;
import com.asuarezr.services.dtos.TransactionResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TransactionService {

  private final TransactionServiceRepository transactionServiceRepository;

  @Inject
  public TransactionService(TransactionServiceRepository transactionServiceRepository) {
    this.transactionServiceRepository = transactionServiceRepository;
  }

  public Uni<TransactionResponseDto> process(TransactionDto transaction){
      return this.transactionServiceRepository.save(transaction);
  }
}

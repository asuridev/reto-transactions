package com.asuarezr.persistence;

import com.asuarezr.persistence.mapper.TransactionMapper;
import com.asuarezr.services.TransactionServiceRepository;
import com.asuarezr.services.dtos.TransactionDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TransactionServiceRepositoryImpl implements TransactionServiceRepository {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  @Inject
  public TransactionServiceRepositoryImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public void save(TransactionDto transactionDto) {
     TransactionEntity transactionEntity = this.transactionMapper.toEntity(transactionDto);
     this.transactionRepository.persist(transactionEntity);
  }
}

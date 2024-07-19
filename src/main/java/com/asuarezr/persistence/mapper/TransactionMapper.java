package com.asuarezr.persistence.mapper;

import com.asuarezr.persistence.TransactionEntity;
import com.asuarezr.services.dtos.TransactionDto;


public interface TransactionMapper {
  TransactionEntity toEntity(TransactionDto transactionDto);
  TransactionDto toDto(TransactionEntity transactionEntity);
}

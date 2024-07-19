package com.asuarezr.persistence.mapper;

import com.asuarezr.persistence.TransactionEntity;
import com.asuarezr.services.dtos.TransactionDto;
import com.asuarezr.services.dtos.TransactionResponseDto;


public interface TransactionMapper {
  TransactionEntity toEntity(TransactionDto transactionDto);
  TransactionResponseDto toDto(TransactionEntity transactionEntity);
}

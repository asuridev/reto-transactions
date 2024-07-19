package com.asuarezr.persistence.mapper;

import com.asuarezr.persistence.TransactionEntity;
import com.asuarezr.services.dtos.TransactionDto;
import com.asuarezr.services.dtos.TransactionResponseDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionMapperImpl implements TransactionMapper{
  
  @Override
  public TransactionEntity toEntity(TransactionDto transactionDto) {
    return new TransactionEntity(
            null,
            transactionDto.transactionId(),
            transactionDto.timestamp(),
            transactionDto.deviceNumber(),
            transactionDto.userId(),
            transactionDto.geoPosition(),
            transactionDto.amount()
    );
  }

  @Override
  public TransactionResponseDto toDto(TransactionEntity transactionEntity) {
    return new TransactionResponseDto(
            transactionEntity.getId(),
            transactionEntity.getTransactionId(),
            transactionEntity.getTimestamp(),
            transactionEntity.getDeviceNumber(),
            transactionEntity.getUserId(),
            transactionEntity.getGeoPosition(),
            transactionEntity.getAmount()
    );
  }
}

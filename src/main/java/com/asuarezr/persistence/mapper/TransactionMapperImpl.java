package com.asuarezr.persistence.mapper;

import com.asuarezr.persistence.TransactionEntity;
import com.asuarezr.services.dtos.TransactionDto;
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
  public TransactionDto toDto(TransactionEntity transactionEntity) {
    return new TransactionDto(
            transactionEntity.getTransactionId(),
            transactionEntity.getTimestamp(),
            transactionEntity.getDeviceNumber(),
            transactionEntity.getUserId(),
            transactionEntity.getGeoPosition(),
            transactionEntity.getAmount()
    );
  }
}

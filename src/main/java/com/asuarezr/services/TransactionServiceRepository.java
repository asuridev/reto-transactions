package com.asuarezr.services;

import com.asuarezr.services.dtos.TransactionDto;


public interface TransactionServiceRepository {

  void save(TransactionDto transactionDto);

}

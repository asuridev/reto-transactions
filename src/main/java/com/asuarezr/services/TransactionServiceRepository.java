package com.asuarezr.services;

import com.asuarezr.services.dtos.TransactionDto;
import com.asuarezr.services.dtos.TransactionResponseDto;
import io.smallrye.mutiny.Uni;


public interface TransactionServiceRepository {

  Uni<TransactionResponseDto> save(TransactionDto transactionDto);

}

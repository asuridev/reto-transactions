package com.asuarezr.services;

import com.asuarezr.services.dtos.TransactionDto;
import com.asuarezr.services.dtos.TransactionResponseDto;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.Document;

import java.time.LocalDate;



public interface TransactionServiceRepository {

  Uni<TransactionResponseDto> save(TransactionDto transactionDto);

  Multi<Document> totalByDate(LocalDate day);

}

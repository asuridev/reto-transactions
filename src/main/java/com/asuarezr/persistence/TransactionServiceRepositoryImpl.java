package com.asuarezr.persistence;

import com.asuarezr.persistence.mapper.TransactionMapper;
import com.asuarezr.services.TransactionServiceRepository;
import com.asuarezr.services.dtos.TransactionDto;
import com.asuarezr.services.dtos.TransactionResponseDto;
import com.mongodb.BasicDBObject;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.time.LocalDate;
import java.util.List;

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
  public Uni<TransactionResponseDto> save(TransactionDto transactionDto) {
     TransactionEntity transactionEntity = this.transactionMapper.toEntity(transactionDto);
     return this.transactionRepository.persist(transactionEntity)
             .onItem().transform(transactionMapper::toDto);
  }

  @Override
  public Multi<Document> totalByDate(LocalDate date) {
     return this.transactionRepository.mongoDatabase().getCollection("transactions")
     .aggregate(List.of(
             new BasicDBObject("$match",
                     new BasicDBObject("timestamp",
                             new BasicDBObject("$gte", date).append("$lt", date.plusDays(1)))),
             new BasicDBObject("$group",
                     new BasicDBObject("_id", new BasicDBObject("day",
                             new BasicDBObject("$dayOfYear","$timestamp"))
                             .append("year", new BasicDBObject("$year","$timestamp")))
                             .append("totalAmount", new BasicDBObject("$sum", "$amount"))
                             .append("count",new BasicDBObject("$sum", 1)))
     ));
  }

}

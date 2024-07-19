package com.asuarezr.persistence;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionRepository implements ReactivePanacheMongoRepository<TransactionEntity> {
}

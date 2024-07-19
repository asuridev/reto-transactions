package com.asuarezr.controller;

import com.asuarezr.persistence.TransactionEntity;
import com.asuarezr.services.TransactionService;
import com.asuarezr.services.dtos.TransactionDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class TransactionController {

  private final TransactionService transactionService;

  @Inject
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @POST
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionDto processing(TransactionDto transaction){
      return transactionService.process(transaction);
    }

}

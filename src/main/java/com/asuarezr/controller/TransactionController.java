package com.asuarezr.controller;

import com.asuarezr.persistence.TransactionEntity;
import com.asuarezr.services.TransactionService;
import com.asuarezr.services.dtos.TransactionDto;
import com.asuarezr.services.dtos.TransactionResponseDto;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.Document;

import java.time.LocalDate;

@Path("transaction")
public class TransactionController {

  private final TransactionService transactionService;

  @Inject
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Multi<Document> report(@QueryParam("date") @NotNull LocalDate date){
    return this.transactionService.report(date);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<TransactionResponseDto> process(@Valid TransactionDto transaction){
      return transactionService.process(transaction);
  }

}

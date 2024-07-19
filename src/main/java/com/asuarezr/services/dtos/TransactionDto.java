package com.asuarezr.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record TransactionDto(
        @NotBlank
        String transactionId,
        @NotNull
        Timestamp timestamp,
        @NotNull
        Long deviceNumber,
        @NotNull
        Long userId,
        @NotNull
        GeoPosition geoPosition,
        @NotNull
        Double amount
) { }

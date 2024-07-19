package com.asuarezr.services.dtos;

import org.bson.types.ObjectId;

import java.sql.Timestamp;

public record TransactionResponseDto(
        ObjectId id,
        String transactionId,
        Timestamp timestamp,
        Long deviceNumber,
        Long userId,
        GeoPosition geoPosition,
        Double amount
) { }

package com.asuarezr.services.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TransactionDto(
        String transactionId,
        Timestamp timestamp,
        Long deviceNumber,
        Long userId,
        GeoPosition geoPosition,
        BigDecimal amount
) { }

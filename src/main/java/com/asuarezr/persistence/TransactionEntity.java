package com.asuarezr.persistence;

import com.asuarezr.services.dtos.GeoPosition;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.sql.Timestamp;


@MongoEntity(collection = "transactions")
public class TransactionEntity {
  public ObjectId id;
  public String transactionId;
  public Timestamp timestamp;
  public Long deviceNumber;
  public Long userId;
  public GeoPosition geoPosition;
  public Double amount;

  public TransactionEntity(ObjectId id, String transactionId, Timestamp timestamp, Long deviceNumber, Long userId, GeoPosition geoPosition, Double amount) {
    this.id = id;
    this.transactionId = transactionId;
    this.timestamp = timestamp;
    this.deviceNumber = deviceNumber;
    this.userId = userId;
    this.geoPosition = geoPosition;
    this.amount = amount;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public Long getDeviceNumber() {
    return deviceNumber;
  }

  public Long getUserId() {
    return userId;
  }

  public GeoPosition getGeoPosition() {
    return geoPosition;
  }

  public Double getAmount() {
    return amount;
  }
}

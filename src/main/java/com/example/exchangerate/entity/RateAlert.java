package com.example.exchangerate.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;  
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "rate_alert")
public class RateAlert {

    @Id 
    @GeneratedValue 
    private UUID id;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    private String currencyPair;
    
    private double targetRate;

    @ManyToOne 
    @JoinColumn (name = "subscriber_id")
    private Subscriber subscriber;

    @ManyToOne 
    @JoinColumn (name = "analyst_id")
    private Analyst analyst;

    
public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}
    
public LocalDate getExpiryDate() {
    return expiryDate;
}

public void setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
}

public AlertStatus getStatus() {
    return status;
}

public void setStatus(AlertStatus status) {
    this.status = status;
}

public String getCurrencyPair() {
    return currencyPair;
}

public void setCurrencyPair(String currencyPair) {
    this.currencyPair = currencyPair;
}

public double getTargetRate() {
    return targetRate;
}

public void setTargetRate(double targetRate) {
    this.targetRate = targetRate;
}

public Subscriber getSubscriber() {
    return subscriber;
}

public void setSubscriber(Subscriber  subscriber) {
    this.subscriber = subscriber;
}

public Analyst getAnalyst() {
    return analyst;
}

public void setAnalyst(Analyst  analyst) {
    this.analyst = analyst;
}


}
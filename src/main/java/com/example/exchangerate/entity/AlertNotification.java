package com.example.exchangerate.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alert_notification")
public class AlertNotification {

    @Id
    @GeneratedValue
    private UUID id;

    private String channel;

    private String message;

    private int retryCount;

    private LocalDate sentDate;

    @ManyToOne
    @JoinColumn(name = "rate_alert_id")
    private RateAlert rateAlert;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public RateAlert getRateAlert() {
        return rateAlert;
    }

    public void setRateAlert(RateAlert rateAlert) {
        this.rateAlert = rateAlert;
    }
}
package com.example.exchangerate.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.exchangerate.entity.AlertNotification;
import com.example.exchangerate.entity.RateAlert;

public interface AlertNotificationRepository extends JpaRepository<AlertNotification, UUID> {

    // E2: JPQL with path navigation (notification -> rate alert -> subscriber -> code), newest first
    @Query("SELECT d FROM AlertNotification d WHERE d.rateAlert.subscriber.subscriberCode = :subscriberCode ORDER BY d.sentDate DESC")
    List<AlertNotification> findHistoryBySubscriberCode(@Param("subscriberCode") String subscriberCode);

    // E3: DELIVERED QUERY . DOES THIS RATE ALERT HAVE AT LEAST ONE NOTIFICATION?
    boolean existsByRateAlert(RateAlert rateAlert);
}
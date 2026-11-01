package com.example.exchangerate.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exchangerate.entity.AlertStatus;
import com.example.exchangerate.entity.Analyst;
import com.example.exchangerate.entity.RateAlert;

public interface RateAlertRepository extends JpaRepository<RateAlert, UUID> {

    // B2: DERIVED query (enum parameter, OrderBy)
    List<RateAlert> findByStatusOrderByExpiryDateAsc(AlertStatus status);

    // B3: DERIVED query (Between is inclusive on both ends)
    List<RateAlert> findByExpiryDateBetweenOrderByExpiryDateAsc(LocalDate start, LocalDate end);

    // D1: DERIVED query . does this analyst already ave an alert on this date
    // whose status is NOT the given status (we pass DEACTIVATED)
    boolean existsByAnalystAndExpiryDateAndStatusNot(Analyst analyst,LocalDate expiryDate, AlertStatus status);
}
package com.example.exchangerate.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    // F1: JPQL with GROUP BY and COUNT. Each row is [status, count].
    // A status with zero rate alerts has no rows, so it does not appear.
    @Query("SELECT r.status, COUNT(r) FROM RateAlert r GROUP BY r.status")
    List<Object[]> countByStatusReport();

    // F2: JPQL UPDATE. One query changes all the rows, no Java loop.
    // It returns the number of rows changed.
    @Modifying
    @Query("UPDATE RateAlert r SET r.status = com.example.exchangerate.entity.AlertStatus.DEACTIVATED "
            + "WHERE r.analyst.id = :analystId AND r.expiryDate = :date "
            + "AND r.status <> com.example.exchangerate.entity.AlertStatus.NOTIFIED")
    int cancelDay(@Param("analystId") UUID analystId, @Param ("date") LocalDate date);
}
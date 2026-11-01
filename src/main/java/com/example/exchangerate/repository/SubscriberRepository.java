package com.example.exchangerate.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.exchangerate.entity.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {

    // B1: DERIVED query
    List<Subscriber> findByCountryIgnoreCaseOrderByLastNameAsc(String country);

    // C4: JPQL with DISTINCT, so a subscriber appears once even with many rate alerts
    @Query("SELECT DISTINCT r.subscriber FROM RateAlert r WHERE r.analyst.id = :analystId")
    List<Subscriber> findSubscribersOfAnalyst(@Param("analystId") UUID analystId);
}
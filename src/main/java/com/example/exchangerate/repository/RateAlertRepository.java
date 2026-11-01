package com.example.exchangerate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exchangerate.entity.RateAlert;

public interface RateAlertRepository extends  JpaRepository<RateAlert, UUID> {
}
package com.example.exchangerate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exchangerate.entity.Analyst;

public interface AnalystRepository extends  JpaRepository<Analyst, UUID> {
}
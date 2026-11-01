package com.example.exchangerate.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.exchangerate.entity.Analyst;

public interface AnalystRepository extends JpaRepository<Analyst, UUID> {

    // C1: JPQL with a JOIN on the currencies list, ignoring letter case
    @Query("SELECT a FROM Analyst a JOIN a.currencies c WHERE LOWER(c.name) = LOWER(:name)")
    List<Analyst> searchByCurrencyName(@Param("name") String name);
}
package com.example.exchangerate.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.exchangerate.entity.Desk;

public interface DeskRepository extends JpaRepository<Desk, UUID> {

    // C2: desks that no analyst occupies, sorted by desk number
    @Query("SELECT d FROM Desk d WHERE NOT EXISTS (SELECT a FROM Analyst a WHERE a.desk = d) ORDER BY d.deskNumber")
    List<Desk> findFreeDesks();
}
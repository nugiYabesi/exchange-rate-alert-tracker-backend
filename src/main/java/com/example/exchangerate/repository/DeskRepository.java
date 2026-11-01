package com.example.exchangerate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exchangerate.entity.Desk;

public interface DeskRepository extends JpaRepository<Desk, UUID> {

}
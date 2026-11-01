package com.example.exchangerate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exchangerate.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

}

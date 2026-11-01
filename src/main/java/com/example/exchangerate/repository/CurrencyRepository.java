package com.example.exchangerate.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.exchangerate.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

    // C3: currencies that no analyst holds (IS EMPTY, no counting)
    @Query("SELECT c FROM Currency c WHERE c.analysts IS EMPTY")
    List<Currency> findUnusedCurrencies();
}
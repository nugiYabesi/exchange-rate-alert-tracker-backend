package com.example.exchangerate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exchangerate.entity.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {
    
}
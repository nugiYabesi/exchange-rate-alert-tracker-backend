package com.example.exchangerate.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "currency")
public class Currency {

    @Id 
    @GeneratedValue
    private UUID id;

    private String name;

    @JsonIgnore 
    @ManyToMany(mappedBy = "currencies")
    private List<Analyst> analysts;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Analyst> getAnalysts() {
        return analysts;

    }

    public void setAnalysts(List<Analyst> analysts) {
        this.analysts = analysts;
    }

}
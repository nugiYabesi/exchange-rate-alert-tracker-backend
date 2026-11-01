package com.example.exchangerate.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "analyst")
public class Analyst {

    @Id 
    @GeneratedValue 
    private UUID id;

    private String firstName;

    private String lastName;
    
    private String email;

    @ManyToOne 
    @JoinColumn (name = "desk_id")
    private Desk desk;

    @ManyToMany 
    @JoinTable (
        name = "analyst_currency",
        joinColumns = @JoinColumn (name = "analyst_id"),
        inverseJoinColumns = @JoinColumn (name = "currency_id")
    
    )

    private List<Currency> currencies = new ArrayList<>();

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}
    
public String  getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String  getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String  getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public Desk  getDesk() {
    return desk;
}

public void setDesk(Desk desk) {
    this.desk = desk;
}

public List<Currency> getCurrencies() {
    return currencies;
}

public void setCurrencies(List<Currency>  currencies) {
    this.currencies = currencies;
}

}
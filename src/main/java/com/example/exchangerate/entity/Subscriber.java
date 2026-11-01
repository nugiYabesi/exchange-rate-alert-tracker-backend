package com.example.exchangerate.entity;

import  java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "subscriber")
public  class Subscriber {

    @Id 
    @GeneratedValue 
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String subscriberCode;

    private String email;

    private String country;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName= firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName= lastName;
    }


    public String getSubscriberCode() {
        return subscriberCode;
    }

    public void setSubscriberCode(String subscriberCode){
        this.subscriberCode= subscriberCode;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email= email;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country){
        this.country= country;
    }

}
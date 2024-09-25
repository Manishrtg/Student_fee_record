package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private  Long fees;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public Long getFees() {
        return fees;
    }

    public void setFees(Long id) {
        this.fees = fees;
    }
    // yaha se add kiye hai

    public void depositPartialFee(Long amount) {
        if (amount <= this.fees) {
            this.fees -= amount;
        } else {
            throw new IllegalArgumentException("Amount exceeds the total fees.");
        }
    }



    // yaha tak
}

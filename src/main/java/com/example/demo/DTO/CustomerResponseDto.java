package com.example.demo.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerResponseDto {

    private Long id;


    private String name;


    private String address;

    private String phoneNumber;

    private String email;
    private String gender;
    private String country;
    private String city;
    private String creditCardType;
    private Integer childrenCount;
    private Boolean isMarried;
    private double salary;

    public CustomerResponseDto( String name, String address, String phoneNumber, String email, String gender, String country, String city, String creditCardType, Integer childrenCount, Boolean isMarried, double salary) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.creditCardType = creditCardType;
        this.childrenCount = childrenCount;
        this.isMarried = isMarried;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

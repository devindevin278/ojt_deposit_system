package com.depositsystem.depositsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table
public class Customer {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cin;

    private String name;
    private String NIK;
    private String email;
    private String phone;
    private String Address;
    private LocalDate DOB;

//    @OneToMany(mappedBy = "customer")
//    private List<Deposit> deposits = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, String NIK, String email, String phone, String address, LocalDate DOB) {
        this.name = name;
        this.NIK = NIK;
        this.email = email;
        this.phone = phone;
        Address = address;
        this.DOB = DOB;
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cin=" + cin +
                ", name='" + name + '\'' +
                ", NIK='" + NIK + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", Address='" + Address + '\'' +
                ", DOB=" + DOB +
//                ", deposits=" + deposits +
                '}';
    }

}

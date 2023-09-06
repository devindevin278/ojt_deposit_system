package com.depositsystem.depositsystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private Integer pin;
    private Double balance;
    private Long cin;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

    @OneToMany(mappedBy = "deposit")
    private List<Transaction>transactions = new ArrayList<>();

    public Deposit(Integer pin, Double balance, Long cin, Status status, Date created) {
        this.pin = pin;
        this.balance = balance;
        this.cin = cin;
        this.status = status;
        this.created = created;
    }

    public Deposit() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //    public Customer getCustomer() {
//        return customer;
//    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }


    @Override
    public String toString() {
        return "Deposit{" +
                "accountId=" + accountId +
                ", pin=" + pin +
                ", balance=" + balance +
                ", cin=" + cin +
                ", status=" + status +
                ", created=" + created +
                ", transactions=" + transactions +
                '}';
    }
}

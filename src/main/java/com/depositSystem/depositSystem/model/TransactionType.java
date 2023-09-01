package com.depositSystem.depositSystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "transactionType")
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionType() {
    }

    public TransactionType(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", transactions=" + transactions +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

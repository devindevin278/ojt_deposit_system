package com.depositsystem.depositsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "status")
    private List<Deposit> deposit;

    public Status() {
    }

    public Status(String name, List<Deposit> deposit) {
        this.name = name;
        this.deposit = deposit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Deposit> getDeposit() {
//        return deposit;
//    }

    public void setDeposit(List<Deposit> deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", deposit=" + deposit +
                '}';
    }
}

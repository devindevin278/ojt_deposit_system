package com.depositsystem.depositsystem.repository;

import com.depositsystem.depositsystem.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findAllByCin(Long cin);
}

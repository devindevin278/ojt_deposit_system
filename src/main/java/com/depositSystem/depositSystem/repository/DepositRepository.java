package com.depositSystem.depositSystem.repository;

import com.depositSystem.depositSystem.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findAllByCustomerCin(Long cin);
}

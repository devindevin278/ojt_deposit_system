package com.depositsystem.depositsystem.repository;

import com.depositsystem.depositsystem.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    TransactionType findByName(String name);

}

package com.depositSystem.depositSystem.repository;

import com.depositSystem.depositSystem.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}

package com.depositSystem.depositSystem.repository;

import com.depositSystem.depositSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

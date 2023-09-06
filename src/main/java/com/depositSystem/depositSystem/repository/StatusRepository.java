package com.depositsystem.depositsystem.repository;

import com.depositsystem.depositsystem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}

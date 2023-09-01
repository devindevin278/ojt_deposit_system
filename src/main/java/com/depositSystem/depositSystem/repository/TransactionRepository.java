package com.depositSystem.depositSystem.repository;

import com.depositSystem.depositSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM Transaction t WHERE t.deposit_id = :deposit_id", nativeQuery = true)
    List<Transaction> findAllByDeposit(@Param("deposit_id") Long deposit_id);

    @Query(value = "SELECT * FROM Transaction t WHERE t.deposit_id = :deposit_id AND DATEDIFF(date(t.created), :start ) >= 0 AND DATEDIFF(date(t.created), :finish ) <= 0", nativeQuery = true)
    List<Transaction> findAllByDate(@Param("deposit_id") Long deposit_id, @Param("start") Date start,  @Param("finish") Date finish);
}

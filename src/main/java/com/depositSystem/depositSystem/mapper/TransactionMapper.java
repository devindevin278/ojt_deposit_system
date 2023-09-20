package com.depositsystem.depositsystem.mapper;

import com.depositsystem.depositsystem.model.Deposit;
import com.depositsystem.depositsystem.model.Transaction;
import com.depositsystem.depositsystem.model.TransactionType;
import com.depositsystem.depositsystem.modeldto.TransactionDto;
import com.depositsystem.depositsystem.repository.DepositRepository;
import com.depositsystem.depositsystem.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionMapper {
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private DepositRepository depositRepository;

    public TransactionDto toDto(Transaction transaction) {
        Double nominal = transaction.getNominal();
        Date created = transaction.getCreated();
        Long deposit_id = transaction.getDeposit().getAccountId();
        String transactionType = transaction.getTransactionType().getName();

        return new TransactionDto(nominal, created, deposit_id, transactionType);
    }

    public Transaction toTransaction(TransactionDto transactionDto) {
        Double nominal = transactionDto.getNominal();
        Date created = transactionDto.getCreated();
        Deposit deposit = depositRepository.findById(transactionDto.getDeposit_id()).orElse(null);
        TransactionType transactionType = transactionTypeRepository.findByName(transactionDto.getTransactionType());

        Transaction transaction = new Transaction(nominal, created, deposit, transactionType);
        return transaction;
    }

}

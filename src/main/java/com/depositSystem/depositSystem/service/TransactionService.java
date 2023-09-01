package com.depositSystem.depositSystem.service;

import com.depositSystem.depositSystem.model.Customer;
import com.depositSystem.depositSystem.model.Deposit;
import com.depositSystem.depositSystem.model.Transaction;
import com.depositSystem.depositSystem.model.TransactionType;
import com.depositSystem.depositSystem.repository.CustomerRepository;
import com.depositSystem.depositSystem.repository.DepositRepository;
import com.depositSystem.depositSystem.repository.TransactionRepository;
import com.depositSystem.depositSystem.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private DepositRepository depositRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionTypeRepository transactionTypeRepository, DepositRepository depositRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.depositRepository = depositRepository;
    }

    public Transaction addTransaction(Double nominal, Long type_id, Long deposit_id) {
        TransactionType transactionType = transactionTypeRepository.findById(type_id).orElse(null);
        Deposit deposit = depositRepository.findById(deposit_id).orElse(null);

        Double balance = deposit.getBalance();

        if(type_id == 1) {
            deposit.setBalance(balance + nominal);
        } else {
            // if balance is insufficient
            if(balance < nominal) {
                return null;
            } else {
                deposit.setBalance(balance - nominal);
            }
        }

        Transaction transaction = new Transaction(nominal, null, deposit, transactionType);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> showTransaction(Long deposit_id) {
        return transactionRepository.findAllByDeposit(deposit_id);
    }

    public List<Transaction> showTransactionByDate(Long deposit_id, Date startDate, Date finishDate) {
        return transactionRepository.findAllByDate(deposit_id, startDate, finishDate);
    }

}

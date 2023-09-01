package com.depositSystem.depositSystem.controller;

import com.depositSystem.depositSystem.model.Deposit;
import com.depositSystem.depositSystem.model.Transaction;
import com.depositSystem.depositSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("addTransaction/{type}")
    public Transaction addTransaction(@RequestParam Double nominal, @RequestParam Long deposit_id, @PathVariable String type) {
        long type_id = 0;

        switch (type) {
            case "kredit":
                type_id = 1;
                break;
            case "debit":
                type_id = 2;
                break;
        }

        return transactionService.addTransaction(nominal, type_id, deposit_id);
    }

    @PostMapping("showTransaction")
    public List<Transaction> showTransaction(@RequestParam Long deposit_id) {
        return transactionService.showTransaction(deposit_id);
    }

    @PostMapping("showTransaction/filterByDate")
    public List<Transaction> showTransaction(@RequestParam Long deposit_id, Date startDate, Date finishDate) {
        return transactionService.showTransactionByDate(deposit_id, startDate, finishDate);
    }
    // date = "mm/dd/yyyy"
}

package com.depositsystem.depositsystem.controller;

import com.depositsystem.depositsystem.mapper.TransactionMapper;
import com.depositsystem.depositsystem.model.Transaction;
import com.depositsystem.depositsystem.modeldto.StatusMessageDto;
import com.depositsystem.depositsystem.modeldto.TransactionDto;
import com.depositsystem.depositsystem.service.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private TransactionService transactionService;
    private TransactionMapper transactionMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("addTransaction/{type}")
    public ResponseEntity<?> addTransaction(@RequestParam Double nominal, @RequestParam Long deposit_id, @PathVariable String type) {
        long type_id = 0;

        switch (type) {
            case "kredit":
                type_id = 1;
                break;
            case "debit":
                type_id = 2;
                break;
        }

        StatusMessageDto<TransactionDto> responseMsg = new StatusMessageDto<TransactionDto>();
        try {
            Transaction newTransaction = transactionService.addTransaction(nominal, type_id, deposit_id);

            if (newTransaction != null) {
                TransactionDto transactionDto = transactionMapper.toDto(newTransaction);
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Transaction created successfully");
                responseMsg.setData(transactionDto);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
                responseMsg.setMessage("Insufficient balance");

                return ResponseEntity.badRequest().body(responseMsg);
            }
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(responseMsg);
        }
    }

    @PostMapping("showTransaction")
    public ResponseEntity<?> showTransaction(@RequestParam Long deposit_id) {
        StatusMessageDto<List<TransactionDto>> responseMsg = new StatusMessageDto<List<TransactionDto>>();

        try{
            List<Transaction> transactions = transactionService.showTransaction(deposit_id);
            List<TransactionDto> transactionsDto = new ArrayList<TransactionDto>();
            for (Transaction item:transactions) {
                transactionsDto.add(transactionMapper.toDto(item));
            }

            if(!transactionsDto.isEmpty()) {
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Transaction shown successfully");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(responseMsg);
        }

    }

    @PostMapping("showTransaction/filterByDate")
    public ResponseEntity<?> showTransaction(@RequestParam Long deposit_id, Date startDate, Date finishDate) {

        StatusMessageDto<List<TransactionDto>> responseMsg = new StatusMessageDto<List<TransactionDto>>();

        try{
            List<Transaction> transactions = transactionService.showTransactionByDate(deposit_id, startDate, finishDate);
            List<TransactionDto> transactionsDto = new ArrayList<TransactionDto>();
            for (Transaction item:transactions) {
                transactionsDto.add(transactionMapper.toDto(item));
            }

            if(!transactionsDto.isEmpty()) {
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Transaction shown successfully");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(responseMsg);
        }
    }
    // date = "mm/dd/yyyy"
}

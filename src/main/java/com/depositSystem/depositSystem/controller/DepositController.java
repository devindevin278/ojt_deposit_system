package com.depositsystem.depositsystem.controller;

import com.depositsystem.depositsystem.mapper.DepositMapper;
import com.depositsystem.depositsystem.model.Deposit;
import com.depositsystem.depositsystem.modeldto.DepositDto;
import com.depositsystem.depositsystem.modeldto.StatusMessageDto;
import com.depositsystem.depositsystem.service.DepositService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("deposit")
public class DepositController {
    private DepositService depositService;
    private DepositMapper depositMapper;

    @Autowired
    public DepositController(DepositService depositService, DepositMapper depositMapper) {
        this.depositService = depositService;
        this.depositMapper = depositMapper;
    }


    @PostMapping("addDeposit")
    public ResponseEntity<?> addDeposit(@RequestParam Long cin, @RequestParam int pin) {
        StatusMessageDto<DepositDto> responseMsg = new StatusMessageDto<DepositDto>();
        try {
            Deposit deposit = depositService.addDeposit(cin, pin);
            DepositDto depositDto = depositMapper.toDto(deposit);

            responseMsg.setStatus(HttpStatus.CREATED.value());
            responseMsg.setMessage("Deposit added successfully");
            responseMsg.setData(depositDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseMsg);
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }

    }

    @PostMapping("showDepositByCin")
    public ResponseEntity<?> showDeposits(@RequestParam Long cin) {
        StatusMessageDto<List<DepositDto>> responseMsg = new StatusMessageDto<List<DepositDto>>();
        try {
            List<Deposit> deposits = depositService.showDeposits(cin);
            List<DepositDto> depositDtos = new ArrayList<DepositDto>();
            for(Deposit item: deposits) {
                depositDtos.add(depositMapper.toDto(item));
            }

            if (!depositDtos.isEmpty()) {
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Shown successfully");
                responseMsg.setData(depositDtos);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
//            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }
    }

    @PostMapping("showDepositById")
    public ResponseEntity<?> showDeposit(@RequestParam Long account_id) {
        StatusMessageDto<DepositDto> responseMsg = new StatusMessageDto<DepositDto>();
        try {
            Deposit deposit = depositService.showDeposit(account_id);

            if (deposit != null) {
                DepositDto depositDto = depositMapper.toDto(deposit);
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Shown successfully");
                responseMsg.setData(depositDto);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }
    }

    @PostMapping("closeDeposit")
    public ResponseEntity<?> closeDeposit(@RequestParam Long account_id) {
        StatusMessageDto<DepositDto> responseMsg = new StatusMessageDto<DepositDto>();
        try {
            Deposit deposit = depositService.closeDeposit(account_id);
            DepositDto depositDto = depositMapper.toDto(deposit);

            responseMsg.setStatus(HttpStatus.OK.value());
            responseMsg.setMessage("Deposit closed successfully");
            responseMsg.setData(depositDto);

            return ResponseEntity.ok().body(responseMsg);
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }
    }

}

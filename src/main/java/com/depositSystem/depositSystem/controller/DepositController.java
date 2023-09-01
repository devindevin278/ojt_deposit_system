package com.depositSystem.depositSystem.controller;

import com.depositSystem.depositSystem.model.Deposit;
import com.depositSystem.depositSystem.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("deposit")
public class DepositController {
    private DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }


    @GetMapping("addDeposit/{cin}")
    public Deposit addDeposit(@PathVariable Long cin) {
        return depositService.addDeposit(cin);
    }

    @GetMapping("showDeposit/{cin}")
    public List<Deposit> showDeposit(@PathVariable Long cin) {
        return depositService.showDeposits(cin);
    }

}

package com.depositsystem.depositsystem.controller;

import com.depositsystem.depositsystem.model.Deposit;
import com.depositsystem.depositsystem.service.DepositService;
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


    @PostMapping("addDeposit")
    public Deposit addDeposit(@RequestParam Long cin, @RequestParam int pin) {
        return depositService.addDeposit(cin, pin);
    }

    @GetMapping("showDeposit/{cin}")
    public List<Deposit> showDeposit(@PathVariable Long cin) {
        return depositService.showDeposits(cin);
    }

    @GetMapping("closeDeposit/{account_id}")
    public Deposit  closeDeposit(@PathVariable Long account_id) {
        return depositService.closeDeposit(account_id);
    }

}

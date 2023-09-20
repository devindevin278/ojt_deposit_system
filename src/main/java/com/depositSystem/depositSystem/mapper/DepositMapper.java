package com.depositsystem.depositsystem.mapper;


import com.depositsystem.depositsystem.model.Deposit;
import com.depositsystem.depositsystem.modeldto.DepositDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DepositMapper {
    public DepositDto toDto(Deposit deposit) {
        Double balance = deposit.getBalance();
        Long account_id = deposit.getAccountId();
        Long cin = deposit.getCin();
        String status = deposit.getStatus().getName();
        Date created = deposit.getCreated();

        return new DepositDto(account_id, balance, cin, status, created);
    }

}

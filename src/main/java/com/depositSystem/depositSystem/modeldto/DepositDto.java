package com.depositsystem.depositsystem.modeldto;

import java.util.Date;

public class DepositDto {
    private Long accountId;
    private Double balance;
    private Long cin;
    private String status;
    private Date created;

    public DepositDto() {
    }

    public DepositDto(Long accountId, Double balance, Long cin, String status, Date created) {
        this.accountId = accountId;
        this.balance = balance;
        this.cin = cin;
        this.status = status;
        this.created = created;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

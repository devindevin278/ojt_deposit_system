package com.depositsystem.depositsystem.modeldto;


import java.util.Date;

public class TransactionDto {
    private Double nominal;
    private Date created;
    private Long deposit_id;
    private String transactionType;

    public TransactionDto() {
    }

    public TransactionDto(Double nominal, Date created, Long deposit_id, String transactionType) {
        this.nominal = nominal;
        this.created = created;
        this.deposit_id = deposit_id;
        this.transactionType = transactionType;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getDeposit_id() {
        return deposit_id;
    }

    public void setDeposit_id(Long deposit_id) {
        this.deposit_id = deposit_id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}

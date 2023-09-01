package com.depositSystem.depositSystem.service;

import com.depositSystem.depositSystem.model.Customer;
import com.depositSystem.depositSystem.model.Deposit;
import com.depositSystem.depositSystem.repository.CustomerRepository;
import com.depositSystem.depositSystem.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {
    private DepositRepository depositRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, CustomerRepository customerRepository) {
        this.depositRepository = depositRepository;
        this.customerRepository = customerRepository;
    }

    public Deposit addDeposit(Long cin) {
        Customer customer = customerRepository.findById(cin).orElse(null);

        Deposit deposit = new Deposit((double) 0, null, customer);

        return depositRepository.save(deposit);
    }

    public List<Deposit> showDeposits(Long cin) {
        return depositRepository.findAllByCustomerCin(cin);
    }
}

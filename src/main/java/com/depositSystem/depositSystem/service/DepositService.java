package com.depositsystem.depositsystem.service;

import com.depositsystem.depositsystem.model.Deposit;
import com.depositsystem.depositsystem.model.Status;
import com.depositsystem.depositsystem.repository.DepositRepository;
import com.depositsystem.depositsystem.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class DepositService {
    private DepositRepository depositRepository;
//    private CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private StatusRepository statusRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, StatusRepository statusRepository , RestTemplate restTemplate) {
        this.depositRepository = depositRepository;
//        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.statusRepository = statusRepository;
    }

    public Deposit addDeposit(Long cin, int pin) {
//        Customer customer = customerRepository.findById(cin).orElse(null);

//        save ke deposit system
        Status status = statusRepository.findById((long)1).orElse(null);
        Deposit deposit = new Deposit(pin, (double) 0, cin, status, null);
        Deposit newDeposit = depositRepository.save(deposit);

//        ambil account_id baru
        Long account_id = newDeposit.getAccountId();

//        save ke customer system
        String apiUrl = "http://localhost:8082/product/addProduct";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("cin", cin)
                .queryParam("account_id", account_id)
                .queryParam("product_id", 1);

        String finalUrl = builder.toUriString();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(finalUrl, null, Void.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return newDeposit;
        } else {
            throw new RuntimeException("Failed to create deposit");
        }

    }

    public List<Deposit> showDeposits(Long cin) {
        return depositRepository.findAllByCin(cin);
    }

    public Deposit closeDeposit(Long account_id) {
        Deposit deposit = depositRepository.findById(account_id).orElse(null);

        deposit.setStatus(statusRepository.findById((long)2).orElse(null));

        depositRepository.save(deposit);

        // save ke customer system
        String apiUrl = "http://localhost:8082/product/closeProduct";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("account_id", account_id)
                .queryParam("product_id", 1);

        String finalUrl = builder.toUriString();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(finalUrl, null, Void.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return deposit;
        } else {
            throw new RuntimeException("Failed to close deposit");
        }

    }

}

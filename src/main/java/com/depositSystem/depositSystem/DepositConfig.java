package com.depositsystem.depositsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DepositConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package com.beaconfire.project.trading.ordermanagement.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri("https://tcs-api.beaconfireinc.com/account-mgmt")
                .build();
    }
}
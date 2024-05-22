package com.beaconfire.project.trading.ordermanagement.config;

import com.beaconfire.project.trading.base.rest.client.RemoteClient;
import com.beaconfire.project.trading.base.rest.client.RemoteClientImpl;
import com.beaconfire.project.trading.base.rest.client.interceptor.AuthHeaderInterceptor;
import com.beaconfire.project.trading.base.rest.client.interceptor.TokenFetcher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RemoteConfig {
    private final String accessKey;
    private final String secretKey;
    private final String accountMgmtBaseUrl;

    public RemoteConfig(@Value("${app.config.access-key}") String accessKey,
                        @Value("${app.config.secret-key}") String secretKey,
                        @Value("${app.config.account-mgmt-base-url}") String accountMgmtBaseUrl) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.accountMgmtBaseUrl = accountMgmtBaseUrl;
    }

    @Bean
    public TokenFetcher tokenFetcher(RestTemplate authsomeClient) {
        return new TokenFetcher(authsomeClient, accessKey, secretKey);
    }

    @Bean
    public AuthHeaderInterceptor authHeaderInterceptor(TokenFetcher tokenFetcher) {
        return new AuthHeaderInterceptor(tokenFetcher);
    }
    @Bean
    public RestTemplate bankRestTemplate(RestTemplateBuilder defaultRestTemplateBuilder, AuthHeaderInterceptor authHeaderInterceptor) {
        return defaultRestTemplateBuilder
                .rootUri(accountMgmtBaseUrl)
                .additionalInterceptors(authHeaderInterceptor)
                .build();
    }

    @Bean
    public RemoteClient bankRemoteClient(RestTemplate bankRestTemplate) {
        return new RemoteClientImpl(bankRestTemplate);
    }

}
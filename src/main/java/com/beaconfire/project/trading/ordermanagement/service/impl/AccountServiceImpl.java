package com.beaconfire.project.trading.ordermanagement.service.impl;

import com.beaconfire.project.trading.base.rest.client.RemoteClient;
import com.beaconfire.project.trading.base.rest.client.response.ServiceResponse;
import com.beaconfire.project.trading.ordermanagement.dto.account.AccountStatusUpdateDto;
import com.beaconfire.project.trading.ordermanagement.exception.BusinessException;
import com.beaconfire.project.trading.ordermanagement.request.account.*;
import com.beaconfire.project.trading.ordermanagement.response.GeneralResponse;
import com.beaconfire.project.trading.ordermanagement.response.account.*;
import com.beaconfire.project.trading.ordermanagement.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final RestTemplate restTemplate;
    private final RemoteClient remoteClient;

    @Override
    public GetAccountsByIdsRequest getAccountsByIdsRequest() {
        List<String> accountIds = Arrays.asList("fa5b50a2-d281-4109-a05b-09a9c3684fbb", "e496a71d-b4e2-44ee-9a15-fa1fa1ba86fa");
        return GetAccountsByIdsRequest.builder().accountIds(accountIds).build();
    }

    @Override
    public GeneralResponse fetchAccountInformationV2(List<String> accountIds){
        ServiceResponse<GeneralResponse> response = remoteClient.request(
                "/api/v1/account/detail/batch",
                HttpMethod.POST,
                getAccountsByIdsRequest(),
                GeneralResponse.class
        );
        return response.getData();
    }

    // 2.2.1
    public GeneralResponse searchAccounts(AccountSearchRequest request) {
        String url = "/api/v1/account/search";
        ServiceResponse<GeneralResponse> response = remoteClient.request(
                url,
                HttpMethod.POST,
                request,
                GeneralResponse.class
        );

        if (response.getData() == null || !response.isSuccess()) {
            throw new BusinessException("Service search account based on criteria");
        }
        return response.getData();
    }


    // 2.2.2 create financial account
    @Override
    public String accountCreation(String nickname, String userId){
        ServiceResponse<AccMgmtCreateResponse> response = remoteClient.request(
                "/api/v1/account", HttpMethod.POST, new AccMgmtCreateRequest(nickname), AccMgmtCreateResponse.class);
        if (response.getData().getData() == null){
            log.info("Null object");
            return "";
        }
        return response.getData().getData().getAccountId();
    }

    // 2.2.3 fetch cash balance
    @Override
    public GeneralResponse fetchCashBalance(String accountId, AccountRequest request) {

        String url = "/api/v1/account/{accountId}/cash-balance";
        ServiceResponse<GeneralResponse> response = remoteClient.request(
                url,
                HttpMethod.POST,
                request,
                GeneralResponse.class,
                accountId
        );

        if (response.getData() == null || !response.isSuccess()) {
            throw new BusinessException("Service failed to fetch cash balance details");
        }
        return response.getData();
    }

    // 2.2.4
    @Override
    public GeneralResponse fetchSecurityBalance(String accountId, AccountRequest request) {
        String url = "/api/v1/account/{accountId}/security-balance";
        ServiceResponse<GeneralResponse> response = remoteClient.request(
                url,
                HttpMethod.POST,
                request,
                GeneralResponse.class,
                accountId
        );

        if (response.getData() == null || !response.isSuccess()) {
            throw new BusinessException("Service failed to fetch security balance details");
        }
        return response.getData();
    }

    // 2.2.5
    @Override
    public GeneralResponse updateAccountStatus(String accountId, StatusUpdateRequest request) {
        AccountStatusUpdateDto dto = new AccountStatusUpdateDto(accountId, request.getStatus());
        String url = "/api/v1/account";
        ServiceResponse<GeneralResponse> response = remoteClient.request(
                url,
                HttpMethod.PATCH,
                dto,
                GeneralResponse.class
        );

        if (response.getData() == null || !response.isSuccess()) {
            throw new BusinessException("Service failed to update account status");
        }
        return response.getData();
    }

    // 2.2.6
    @Override
    public GeneralResponse addCashTransaction(AddCashTransactionRequest request) {
        String url = "/api/v1/account/cash";
        ServiceResponse<GeneralResponse> response = remoteClient.request(
                url,
                HttpMethod.POST,
                request,
                GeneralResponse.class
        );

        if (response.getData() == null || !response.isSuccess()) {
            throw new BusinessException("Service failed to add cash balance to account");
        }
        return response.getData();
    }

    // 2.2.7
    @Override
    public GeneralResponse addSecurityTransaction(AddSecurityTransactionRequest request) {
        String url = "/api/v1/account/security";
        ServiceResponse<GeneralResponse> response = remoteClient.request(
                url,
                HttpMethod.POST,
                request,
                GeneralResponse.class
        );

        if (response.getData() == null || !response.isSuccess()) {
            throw new BusinessException("Service failed to add cash balance to account");
        }
        return response.getData();
    }

}

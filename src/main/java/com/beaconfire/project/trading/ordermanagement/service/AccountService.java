package com.beaconfire.project.trading.ordermanagement.service;

import com.beaconfire.project.trading.ordermanagement.request.account.*;
import com.beaconfire.project.trading.ordermanagement.response.GeneralResponse;
import com.beaconfire.project.trading.ordermanagement.response.account.*;

import java.util.List;

public interface AccountService {
    GetAccountsByIdsRequest getAccountsByIdsRequest();
    GeneralResponse fetchAccountInformationV2(List<String> accountIds);
    public GeneralResponse searchAccounts(AccountSearchRequest request);
    String accountCreation(String nickname, String userId);
    public GeneralResponse fetchCashBalance(String accountId, AccountRequest request);
    public GeneralResponse fetchSecurityBalance(String accountId, AccountRequest request);
    public GeneralResponse updateAccountStatus(String accountId, StatusUpdateRequest request);
    public GeneralResponse addCashTransaction(AddCashTransactionRequest request);
    public GeneralResponse addSecurityTransaction(AddSecurityTransactionRequest request);
}

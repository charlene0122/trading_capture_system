package com.beaconfire.project.trading.ordermanagement.request.account;

import lombok.Data;

@Data
public class AccountCreationRequest {
    private String nickname;
    private String userId;
}
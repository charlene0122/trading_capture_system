package com.beaconfire.project.trading.ordermanagement.request.account;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class AddCashTransactionRequest {
    private String accountId;
    private String amount;
    private String currency;
    private String transactionType;
}

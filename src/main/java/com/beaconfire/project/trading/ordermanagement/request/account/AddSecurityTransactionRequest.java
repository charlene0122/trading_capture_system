package com.beaconfire.project.trading.ordermanagement.request.account;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class AddSecurityTransactionRequest {
    private String accountId;
    private String quantity;
    private String stock;
    private String transactionType;
}

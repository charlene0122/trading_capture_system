package com.beaconfire.project.trading.ordermanagement.response.account;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashBalanceResult {
    private int totalFound;
    private List<CashBalance> balances;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class CashBalance {
    String currency;
    String amount;
}
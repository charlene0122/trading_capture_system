package com.beaconfire.project.trading.ordermanagement.response.account;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityBalanceResult {
    private int totalFound;
    private List<SecurityBalance> balances;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class SecurityBalance {
    String stock;
    String quantity;
}
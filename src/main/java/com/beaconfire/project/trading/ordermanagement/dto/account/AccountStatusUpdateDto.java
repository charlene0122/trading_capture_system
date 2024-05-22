package com.beaconfire.project.trading.ordermanagement.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountStatusUpdateDto {
    private String accountId;
    private String status;
}

package com.beaconfire.project.trading.ordermanagement.dto.account;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccMgmtInfoDto {
    private String accountId;
    private String accountNumber;
    private String nickname;
}
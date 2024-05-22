package com.beaconfire.project.trading.ordermanagement.dto.account;

import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDetailDto {
    private String accountId;
    private String accountNumber;
    private String nickname;
    private Timestamp createdDate;
}
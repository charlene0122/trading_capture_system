package com.beaconfire.project.trading.ordermanagement.dto.account;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSearchDto {
    private String accountId;
    private String accountNumber;
    private String nickname;
    private String userFullName;
    private String userEmail;
    private String accountStatus;
    private String createdDate;
}

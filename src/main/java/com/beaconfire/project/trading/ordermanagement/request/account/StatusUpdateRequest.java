package com.beaconfire.project.trading.ordermanagement.request.account;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class StatusUpdateRequest {
    private String status;
}

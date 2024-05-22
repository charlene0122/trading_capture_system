package com.beaconfire.project.trading.ordermanagement.response.account;

import com.beaconfire.project.trading.ordermanagement.dto.account.AccMgmtInfoDto;
import com.beaconfire.project.trading.ordermanagement.dto.FieldErrors;
import com.beaconfire.project.trading.ordermanagement.response.ServiceStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccMgmtCreateResponse {
    private ServiceStatus serviceStatus;
    private AccMgmtInfoDto data;
    private FieldErrors fielderrors;
}
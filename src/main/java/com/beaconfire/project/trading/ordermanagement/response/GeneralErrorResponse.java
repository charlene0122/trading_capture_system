package com.beaconfire.project.trading.ordermanagement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GeneralErrorResponse {

    private ServiceStatus serviceStatus;
}
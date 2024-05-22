package com.beaconfire.project.trading.ordermanagement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> {
    ServiceStatus serviceStatus;
    T data;
}

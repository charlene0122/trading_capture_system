package com.beaconfire.project.trading.ordermanagement.response;

import lombok.*;
import org.springframework.lang.Nullable;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStatus {
    private boolean success;
    @Nullable
    private String errorMessage;
    @Nullable
    private String statusCode;
}
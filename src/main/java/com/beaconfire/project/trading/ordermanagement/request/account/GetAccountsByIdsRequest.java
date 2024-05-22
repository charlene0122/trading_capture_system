package com.beaconfire.project.trading.ordermanagement.request.account;

import lombok.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GetAccountsByIdsRequest {

    @NonNull
    private List<String> accountIds;
}

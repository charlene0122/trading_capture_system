package com.beaconfire.project.trading.ordermanagement.request.account;

import com.beaconfire.project.trading.ordermanagement.request.SortCriteria;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class AccountSearchRequest {
    private int pageSize = 10;
    private int pageNumber = 0;
    private String searchContent;
    private String accountStatus;
    private SortCriteria sortCriteria;
}

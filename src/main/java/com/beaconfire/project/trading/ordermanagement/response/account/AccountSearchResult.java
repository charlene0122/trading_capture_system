package com.beaconfire.project.trading.ordermanagement.response.account;

import com.beaconfire.project.trading.ordermanagement.dto.account.AccountSearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSearchResult {
    private int totalCount;
    private List<AccountSearchDto> accounts;
}

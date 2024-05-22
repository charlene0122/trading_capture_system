package com.beaconfire.project.trading.ordermanagement.request;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortCriteria {
    private String field;
    private boolean isAsc;
}

package com.beaconfire.project.trading.ordermanagement.response.order;

import com.beaconfire.project.trading.ordermanagement.dto.order.OrderSearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchResult {
    private Long totalCount;
    private List<OrderSearchDto> orders;

}
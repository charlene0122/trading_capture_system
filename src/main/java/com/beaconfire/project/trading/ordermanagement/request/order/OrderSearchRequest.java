package com.beaconfire.project.trading.ordermanagement.request.order;

import com.beaconfire.project.trading.ordermanagement.request.SortCriteria;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderSearchRequest {
    private Integer pageSize;
    private Integer pageNumber;
    private String searchContent;
    private String orderStatus;
    private List<String> instruments;
    private String settleDateStartsAt;
    private String settleDateEndsAt;
    private String side;
    private SortCriteria sortCriteria;
}


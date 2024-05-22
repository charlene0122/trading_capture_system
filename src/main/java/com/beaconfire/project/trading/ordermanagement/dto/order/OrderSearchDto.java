package com.beaconfire.project.trading.ordermanagement.dto.order;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class OrderSearchDto {
    private String orderId;
    private String orderStatus;
    private String orderQuantity;
    private String side;
    private String price;
    private String instrument;
    private String settleDate;
    private Timestamp creationTime;
}

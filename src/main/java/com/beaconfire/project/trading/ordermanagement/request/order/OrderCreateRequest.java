package com.beaconfire.project.trading.ordermanagement.request.order;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderCreateRequest {
    private String orderId;
    private String clOrderId;
    private String orderStatus;
    private String orderQuantity;
    private String side;
    private String orderType;
    private String price;
    private String priceType;
    private String currency;
    private String instrument;
    private String settleType;
    private String settleDate;
    private String interestedParty;
}

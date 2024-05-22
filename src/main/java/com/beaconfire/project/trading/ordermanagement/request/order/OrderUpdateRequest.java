package com.beaconfire.project.trading.ordermanagement.request.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderUpdateRequest {
    private String clientId;
    private String orderQuantity;
    private String side;
    private String price;
    private String priceType;
    private String currency;
    private String instrument;
    private String settleType;
    private String settleDate;
    private String interestedParty;
}

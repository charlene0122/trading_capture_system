package com.beaconfire.project.trading.ordermanagement.dto.order;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
public class OrderDetailDto {
    private String orderId;
    private String clientId;
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
    private String tradeDate;
    private Timestamp creationTime;
    private String interestedParty;
}
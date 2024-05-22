package com.beaconfire.project.trading.ordermanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "`order`")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "cl_order_id")
    private String clientId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "side")
    private Integer side;

    @Column(name = "order_type")
    private Integer orderType;

    @Column(name = "price")
    private String price;

    @Column(name = "price_type")
    private Integer priceType;

    @Column(name = "currency")
    private String currency;

    @Column(name = "instrument_name")
    private String instrument;

    @Column(name = "settle_type")
    private Integer settleType;

    @Column(name = "settle_date")
    private String settleDate;

    @Column(name = "trade_date")
    private String tradeDate;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "interested_party")
    private String interestedParty;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "order_quantity")
    private String orderQuantity;

}

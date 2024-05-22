package com.beaconfire.project.trading.ordermanagement.util;

import com.beaconfire.project.trading.ordermanagement.request.order.OrderCreateRequest;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderDetailDto;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderSearchDto;
import com.beaconfire.project.trading.ordermanagement.entity.*;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderSearchRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderUtil {

    public static Specification<Order> createSpecification(OrderSearchRequest criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getSearchContent() != null) {
                predicates.add(criteriaBuilder.like(root.get("orderId"), "%" + criteria.getSearchContent() + "%"));
            }
            if (criteria.getOrderStatus() != null && !criteria.getOrderStatus().isEmpty()) {
                Integer statusValue = Status.valueOf(criteria.getOrderStatus().toUpperCase()).getCode();
                if (statusValue != null) {
                    predicates.add(criteriaBuilder.equal(root.get("orderStatus"), statusValue));
                }
            }
            if (criteria.getInstruments() != null && !criteria.getInstruments().isEmpty()) {
                CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("instrument"));
                for (String instrument : criteria.getInstruments()) {
                    inClause.value(instrument);
                }
                predicates.add(inClause);
            }
            if (criteria.getSettleDateStartsAt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("settleDate"), criteria.getSettleDateStartsAt()));
            }
            if (criteria.getSettleDateEndsAt() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("settleDate"), criteria.getSettleDateEndsAt()));
            }
            if (criteria.getSide() != null) {
                Integer sideValue = Side.valueOf(criteria.getSide().toUpperCase()).getCode();
                if (sideValue != null) {
                    predicates.add(criteriaBuilder.equal(root.get("side"), sideValue));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static OrderDetailDto orderToDtoConverter(Order order){
        OrderDetailDto dto = OrderDetailDto.builder()
                .orderId(order.getOrderId())
                .clientId(order.getClientId())
                .orderQuantity(order.getOrderQuantity())
                .price(order.getPrice())
                .currency(order.getCurrency())
                .instrument(order.getInstrument())
                .settleDate(order.getSettleDate())
                .interestedParty(order.getInterestedParty())
                .creationTime(order.getCreationTime())
                .build();

        dto.setSide(Side.fromCode(order.getSide()).name());
        dto.setOrderType(OrderType.fromCode(order.getOrderType()).name());
        dto.setPriceType(PriceType.fromCode(order.getPriceType()).name());
        dto.setSettleType(SettleType.fromCode(order.getSettleType()).name().replace("_", " "));
        return dto;
    }

    public static OrderSearchDto orderToSearchDtoConverter(Order order){
        OrderSearchDto dto = OrderSearchDto.builder()
                .orderId(order.getOrderId())
                .orderQuantity(order.getOrderQuantity())
                .price(order.getPrice())
                .instrument(order.getInstrument())
                .settleDate(order.getSettleDate())
                .creationTime(order.getCreationTime())
                .build();
        dto.setSide(Side.fromCode(order.getSide()).name());
        return dto;
    }

    public static Order dtoToOrderConverter(OrderCreateRequest order){
        Order newOrder = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .clientId(order.getClOrderId())
                .orderStatus(0)
                .orderQuantity(order.getOrderQuantity())
                .price(order.getPrice())
                .currency(order.getCurrency())
                .instrument(order.getInstrument())
                .settleDate(order.getSettleDate())
                .interestedParty(order.getInterestedParty())
                .creationTime(new Timestamp(System.currentTimeMillis()))
                // hardcode user ID
                .userId("1")
                .build();
        newOrder.setSide(Side.valueOf(order.getSide().toUpperCase()).getCode());
        newOrder.setOrderType(OrderType.valueOf(order.getOrderType().toUpperCase()).getCode());
        newOrder.setPriceType(PriceType.valueOf(order.getPriceType().toUpperCase()).getCode());
        SettleType settleType = SettleType.valueOf(order.getSettleType().replace(" ", "_").toUpperCase());
        newOrder.setSettleType(settleType.getCode());

        return newOrder;
    }

}

enum Status {
    NEW(1), PARTIALLY_FILLED(2), FILLED(3);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status fromCode(int code) {
        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code for Status: " + code);
    }
}

enum Side {
    BUY(0), SELL(1);

    private final int code;

    Side(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Side fromCode(int code) {
        for (Side side : Side.values()) {
            if (side.getCode() == code) {
                return side;
            }
        }
        throw new IllegalArgumentException("Invalid code for Side: " + code);
    }
}

enum OrderType {
    MARKET(0), LIMIT(1);

    private final int code;

    OrderType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderType fromCode(int code) {
        for (OrderType type : OrderType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code for OrderType: " + code);
    }
}

enum PriceType {
    PERCENTAGE(0), PER_UNIT(1), FIXED_AMOUNT(2);

    private final int code;

    PriceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PriceType fromCode(int code) {
        for (PriceType type : PriceType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code for PriceType: " + code);
    }
}

enum SettleType {
    REGULAR(0), CASH(1), T_PLUS(2), T_PLUS_2(3), T_PLUS_3(4), T_PLUS_4(5);

    private final int code;

    SettleType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SettleType fromCode(int code) {
        for (SettleType type : SettleType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code for SettleType: " + code);
    }
}

package com.beaconfire.project.trading.ordermanagement.service;

import com.beaconfire.project.trading.ordermanagement.request.order.OrderCreateRequest;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderDetailDto;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderSearchDto;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderUpdateRequest;
import com.beaconfire.project.trading.ordermanagement.entity.Order;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    // for testing purposes only
    List<Order> getAllOrders();

    // 2.1.1 list/search all orders given the search requirements
    Page<OrderSearchDto> searchOrders(OrderSearchRequest criteria);

    // 2.1.2 display single order's details given the id
    OrderDetailDto getOrderById(String id);

    // 2.1.3 create a new order
    String createOrder(OrderCreateRequest order);

    // 2.1.4 delete an order
    void deleteOrder(String id);

    // 2.1.5 update an order
    void updateOrder(String id, OrderUpdateRequest order);

}
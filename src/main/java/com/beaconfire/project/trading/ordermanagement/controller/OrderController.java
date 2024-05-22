package com.beaconfire.project.trading.ordermanagement.controller;

import com.beaconfire.project.trading.ordermanagement.request.order.OrderCreateRequest;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderDetailDto;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderSearchDto;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderUpdateRequest;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderSearchRequest;
import com.beaconfire.project.trading.ordermanagement.response.*;
import com.beaconfire.project.trading.ordermanagement.response.order.OrderSearchResult;
import com.beaconfire.project.trading.ordermanagement.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller + @ResponseBody
@AllArgsConstructor
@Validated
@Slf4j
@RequestMapping("api/v1/order")
public class OrderController {
    private OrderService orderService;

    // 2.1.1 list/search all orders given the search requirements
    @PostMapping("/search")
    public ResponseEntity<GeneralResponse> searchOrdersByCriteria(@RequestBody OrderSearchRequest request){
        Page<OrderSearchDto> pages = orderService.searchOrders(request);
        return ResponseEntity.ok(
                GeneralResponse.builder()
                        .serviceStatus(new ServiceStatus(true, "", ""))
                        .data(
                                OrderSearchResult.builder()
                                        .totalCount(pages.getTotalElements())
                                        .orders(pages.getContent())
                                        .build()
                        )
                        .build
                        ());
    }

    // 2.1.2 Get one order by ID
    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getOrderById(@PathVariable String id){
        OrderDetailDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(
                new GeneralResponse(
                        new ServiceStatus(true, "", ""),
                        order
                ));
    }

    // 2.1.3 Create new order
    @PostMapping("")
    public ResponseEntity<GeneralResponse> createOrder(@RequestBody @Validated OrderCreateRequest order){
        String id = orderService.createOrder(order);
        return ResponseEntity.ok(
                new GeneralResponse(
                        new ServiceStatus(true, "", ""),
                        id)
        );
    }

    // 2.1.4 Delete order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(
                new GeneralResponse(
                        new ServiceStatus(true, "", ""),
                        id)
        );
    }

    // 2.1.5 Update order by given ID
    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateOrder(@PathVariable String id, @RequestBody @Validated OrderUpdateRequest order){
        orderService.updateOrder(id, order);
        return ResponseEntity.ok(
                new GeneralResponse(
                        new ServiceStatus(true, "", ""),
                        id)
        );
    }

    // for testing purposes
    @GetMapping("")
    public ResponseEntity<GeneralResponse> getAllOrders(){
        return ResponseEntity.ok(new GeneralResponse(
                new ServiceStatus(true, "", ""),
                orderService.getAllOrders()
        ));
    }

}
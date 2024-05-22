package com.beaconfire.project.trading.ordermanagement.service.impl;

import com.beaconfire.project.trading.base.rest.client.RemoteClient;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderCreateRequest;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderDetailDto;
import com.beaconfire.project.trading.ordermanagement.dto.order.OrderSearchDto;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderUpdateRequest;
import com.beaconfire.project.trading.ordermanagement.entity.Order;
import com.beaconfire.project.trading.ordermanagement.exception.ResourceNotFoundException;
import com.beaconfire.project.trading.ordermanagement.repository.OrderRepo;
import com.beaconfire.project.trading.ordermanagement.request.order.OrderSearchRequest;
import com.beaconfire.project.trading.ordermanagement.service.OrderService;
import com.beaconfire.project.trading.ordermanagement.util.OrderUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final RestTemplate restTemplate;
    private final RemoteClient remoteClient;
    private final OrderRepo orderRepo;

    @Override
    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    @Override
    public Page<OrderSearchDto> searchOrders(OrderSearchRequest criteria) {
        Specification<Order> specification = OrderUtil.createSpecification(criteria);
        Sort sort = Sort.unsorted();
        if (criteria.getSortCriteria() != null && criteria.getSortCriteria().getField() != null) {
            Sort.Direction direction = criteria.getSortCriteria().isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC;
            sort = Sort.by(new Sort.Order(direction, criteria.getSortCriteria().getField()));
        }
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize(), sort);

        Page<Order> page = orderRepo.findAll(specification, pageable);
        return page.map(OrderUtil::orderToSearchDtoConverter);
    }

    @Override
    public OrderDetailDto getOrderById(String id){
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found"));
        return OrderUtil.orderToDtoConverter(order);
    }

    @Override
    public String createOrder(OrderCreateRequest order){
        Order orderToAdd = OrderUtil.dtoToOrderConverter(order);
        orderRepo.save(orderToAdd);
        return orderToAdd.getOrderId();
    }

    @Override
    public void deleteOrder(String id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found"));
        orderRepo.delete(order);
    }

    @Override
    public void updateOrder(String id, OrderUpdateRequest order) {
        Order originalOrder = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found"));
        if (order.getClientId() != null) {
            originalOrder.setClientId(order.getClientId());
        }
        // ... same for all fields in the OrderUpdateDto object
        orderRepo.save(originalOrder);
    }

}
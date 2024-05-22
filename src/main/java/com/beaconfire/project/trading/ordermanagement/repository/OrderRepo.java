package com.beaconfire.project.trading.ordermanagement.repository;

import com.beaconfire.project.trading.ordermanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}

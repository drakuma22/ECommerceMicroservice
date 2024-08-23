package com.microservice.order_service.repository;

import com.microservice.order_service.dto.OrderResponse;
import com.microservice.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<OrderResponse> getByOrderNumber(String orderNumber);
}

package com.microservice.order_service.service;

import com.dev.event.OrderPlacedEvent;
import com.microservice.order_service.client.InventoryClient;
import com.microservice.order_service.dto.OrderRequest;
import com.microservice.order_service.dto.OrderResponse;
import com.microservice.order_service.model.Order;
import com.microservice.order_service.model.UserDetails;
import com.microservice.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

        boolean isInStock = inventoryClient.isInStock(orderRequest.getSkuCode(), orderRequest.getQuantity());

        if(isInStock){
            UserDetails userDetail = new UserDetails();
            userDetail.setEmail(orderRequest.getUserDetails().getEmail());
            userDetail.setFirstName(orderRequest.getUserDetails().getFirstName());
            userDetail.setLastName(orderRequest.getUserDetails().getLastName());

            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.getSkuCode());
            order.setPrice(orderRequest.getPrice());
            order.setQuantity(orderRequest.getQuantity());
            order.setUserDetails(userDetail);

            //Send the message to Kafka Topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.getUserDetails().getEmail());
            orderPlacedEvent.setFirstName(orderRequest.getUserDetails().getFirstName());
            orderPlacedEvent.setLastName(orderRequest.getUserDetails().getLastName());
            kafkaTemplate.send("order-placed", orderPlacedEvent);

            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product with SkuCode " + orderRequest.getSkuCode() + " is not in stock");
        }

    }

    public OrderResponse getOrder(String orderNumber) {
        return orderRepository.getByOrderNumber(orderNumber)
                .orElseThrow(() -> new NoSuchElementException("Order not found with orderNumber " + orderNumber));
    }
}

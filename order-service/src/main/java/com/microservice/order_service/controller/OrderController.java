package com.microservice.order_service.controller;

import com.microservice.order_service.dto.OrderRequest;
import com.microservice.order_service.dto.OrderResponse;
import com.microservice.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrder(@RequestParam String orderNumber){
        return orderService.getOrder(orderNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest ) {
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}

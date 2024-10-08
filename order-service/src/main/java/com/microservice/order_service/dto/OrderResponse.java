package com.microservice.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}

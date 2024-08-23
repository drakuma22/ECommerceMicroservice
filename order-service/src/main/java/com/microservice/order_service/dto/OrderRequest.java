package com.microservice.order_service.dto;

import com.microservice.order_service.model.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
    private UserDetails userDetails;
}

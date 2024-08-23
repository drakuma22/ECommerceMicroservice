package com.microservice.product_service.service;


import com.microservice.product_service.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.product_service.dto.ProductRequest;
import com.microservice.product_service.model.Product;
import com.microservice.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product =  Product.builder()
                            .name(productRequest.getName())
                            .description(productRequest.getDescription())
                            .skuCode(productRequest.getSkuCode())
                            .price(productRequest.getPrice())
                            .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getSkuCode(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                        product.getSkuCode(),
                        product.getPrice()))
                .toList();
    }

}

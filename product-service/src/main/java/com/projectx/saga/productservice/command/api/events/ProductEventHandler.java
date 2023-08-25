package com.projectx.saga.productservice.command.api.events;

import com.projectx.saga.productservice.command.api.data.ProductDetails;
import com.projectx.saga.productservice.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    @Autowired
    private ProductRepository productRepository;

    @EventHandler
    public void onCreate(CreateProductEvent event) {
        ProductDetails productDetails = ProductDetails.builder()
                .productId(event.getProductId())
                .productName(event.getProductName())
                .productDescription(event.getProductDescription())
                .productPrice(event.getProductPrice())
                .productQuantity(event.getProductQuantity())
                .build();
        productRepository.save(productDetails);
    }
}

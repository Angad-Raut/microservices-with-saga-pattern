package com.projectx.saga.productservice.command.api.service;

import com.projectx.saga.commonservice.utils.CommonUtils;
import com.projectx.saga.productservice.command.api.command.CreateProductCommand;
import com.projectx.saga.productservice.command.api.dto.ProductDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private transient CommandGateway commandGateway;

    @Override
    public String createProduct(ProductDto dto) {
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .productName(dto.getProductName())
                .productDescription(dto.getProductDescription())
                .productPrice(dto.getProductPrice())
                .productQuantity(dto.getProductQuantity())
                .build();
        commandGateway.sendAndWait(command);
        return CommonUtils.ADD_PRODUCT;
    }
}

package com.projectx.saga.productservice.command.api.service;

import com.projectx.saga.productservice.command.api.dto.ProductDto;

public interface ProductService {
    String createProduct(ProductDto dto);
}

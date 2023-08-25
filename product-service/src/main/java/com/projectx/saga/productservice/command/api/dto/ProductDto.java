package com.projectx.saga.productservice.command.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
}

package com.projectx.saga.productservice.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductEvent {
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;
}

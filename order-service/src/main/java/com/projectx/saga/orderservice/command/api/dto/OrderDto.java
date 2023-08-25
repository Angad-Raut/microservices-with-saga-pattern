package com.projectx.saga.orderservice.command.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String userId;
    private String productId;
    private Double productPrice;
    private Integer productQty;
}

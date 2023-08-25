package com.projectx.saga.commonservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {
    private String productId;
    private String productName;
    private Double productPrice;
    private Integer productQty;
}

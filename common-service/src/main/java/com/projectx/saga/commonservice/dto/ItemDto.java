package com.projectx.saga.commonservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
    private String productId;
    private Double productPrice;
    private Integer productQty;
}

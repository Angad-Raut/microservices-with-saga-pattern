package com.projectx.saga.orderservice.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderEvent {
    private String orderId;
    private String userId;
    private String productId;
    private Double productPrice;
    private Integer productQty;
    private String orderStatus;
}

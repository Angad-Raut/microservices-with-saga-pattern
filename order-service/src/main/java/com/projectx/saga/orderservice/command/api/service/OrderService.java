package com.projectx.saga.orderservice.command.api.service;

import com.projectx.saga.orderservice.command.api.dto.OrderDto;

public interface OrderService {
    String placeOrder(OrderDto dto);
}

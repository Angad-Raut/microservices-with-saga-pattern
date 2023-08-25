package com.projectx.saga.orderservice.command.api.service;

import com.projectx.saga.commonservice.utils.CommonUtils;
import com.projectx.saga.orderservice.command.api.command.CreateOrderCommand;
import com.projectx.saga.orderservice.command.api.dto.OrderDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private transient CommandGateway commandGateway;

    @Override
    public String placeOrder(OrderDto dto) {
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .productPrice(dto.getProductPrice())
                .productQty(dto.getProductQty())
                .orderStatus(CommonUtils.ORDER_CREATED_STATUS)
                .build();
        commandGateway.sendAndWait(createOrderCommand);
        return CommonUtils.ORDER_PLACED;
    }
}

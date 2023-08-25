package com.projectx.saga.orderservice.command.api.controller;

import com.projectx.saga.commonservice.dto.ResponseDto;
import com.projectx.saga.orderservice.command.api.dto.OrderDto;
import com.projectx.saga.orderservice.command.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    private ResponseEntity<ResponseDto<String>> placeOrder(@RequestBody OrderDto orderDto) {
        String result = orderService.placeOrder(orderDto);
        return new ResponseEntity<ResponseDto<String>>(new ResponseDto<String>(result,null), HttpStatus.CREATED);
    }
}

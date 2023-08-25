package com.projectx.saga.orderservice.command.api.aggregate;

import com.projectx.saga.commonservice.command.CancelOrderCommand;
import com.projectx.saga.commonservice.command.CompleteOrderCommand;
import com.projectx.saga.commonservice.events.CancelOrderEvent;
import com.projectx.saga.orderservice.command.api.command.CreateOrderCommand;
import com.projectx.saga.orderservice.command.api.events.CreateOrderEvent;
import com.projectx.saga.orderservice.command.api.events.OrderCompletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String userId;
    private String productId;
    private Double productPrice;
    private Integer productQty;
    private String orderStatus;

    public OrderAggregate(){

    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        CreateOrderEvent createOrderEvent = new CreateOrderEvent();
        BeanUtils.copyProperties(command,createOrderEvent);
        AggregateLifecycle.apply(createOrderEvent);
    }

    @EventSourcingHandler
    public void on(CreateOrderEvent event) {
        this.orderId=event.getOrderId();
        this.userId =event.getUserId();
        this.orderStatus=event.getOrderStatus();
        this.productId =event.getProductId();
        this.productPrice=event.getProductPrice();
        this.productQty=event.getProductQty();
    }

    @CommandHandler
    public void handle(CompleteOrderCommand command) {
        OrderCompletedEvent orderCompletedEvent = OrderCompletedEvent.builder()
                .orderId(command.getOrderId())
                .orderStatus(command.getOrderStatus())
                .build();
        AggregateLifecycle.apply(orderCompletedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event) {
        this.orderId = event.getOrderId();
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public OrderAggregate(CancelOrderCommand command) {
        CancelOrderEvent cancelOrderEvent = new CancelOrderEvent();
        BeanUtils.copyProperties(command,cancelOrderEvent);
        AggregateLifecycle.apply(cancelOrderEvent);
    }

    @EventSourcingHandler
    public void on(CancelOrderEvent event) {
        this.orderId = event.getOrderId();
        this.orderStatus=event.getOrderStatus();
    }
}

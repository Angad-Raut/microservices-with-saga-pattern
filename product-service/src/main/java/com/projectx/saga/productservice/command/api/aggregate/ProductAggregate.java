package com.projectx.saga.productservice.command.api.aggregate;

import com.projectx.saga.productservice.command.api.command.CreateProductCommand;
import com.projectx.saga.productservice.command.api.events.CreateProductEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productQuantity;

    public ProductAggregate(){}

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        CreateProductEvent createProductEvent = new CreateProductEvent();
        BeanUtils.copyProperties(command,createProductEvent);
        AggregateLifecycle.apply(createProductEvent);
    }

    @EventSourcingHandler
    public void on(CreateProductEvent event) {
        this.productId=event.getProductId();
        this.productName=event.getProductName();
        this.productDescription=event.getProductDescription();
        this.productPrice=event.getProductPrice();
        this.productQuantity=event.getProductQuantity();
    }
}

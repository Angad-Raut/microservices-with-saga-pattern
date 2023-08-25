package com.projectx.saga.paymentservice.command.api.aggregate;

import com.projectx.saga.commonservice.command.PaymentFailedCommand;
import com.projectx.saga.commonservice.command.ValidatePaymentCommand;
import com.projectx.saga.commonservice.events.PaymentFailedEvent;
import com.projectx.saga.commonservice.events.PaymentProcessedEvent;
import com.projectx.saga.commonservice.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {
    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    public PaymentAggregate(){

    }
    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand command) {
          log.info("Executing ValidatePaymentCommand for Order Id : {} and Payment Id : {}",
                  command.getOrderId(),command.getPaymentId());
        PaymentProcessedEvent paymentProcessedEvent = PaymentProcessedEvent
                .builder()
                .paymentId(command.getPaymentId())
                .orderId(command.getOrderId())
                .build();
        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("PaymentProcessedEvent Applied");
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event) {
        this.paymentId=event.getPaymentId();
        this.orderId=event.getOrderId();
    }

    @CommandHandler
    public PaymentAggregate(PaymentFailedCommand command) {
        log.info("PaymentFailedCommand for Order Id : {} and Payment Id : {}",
                command.getOrderId(),command.getPaymentId());
        PaymentFailedEvent paymentFailedEvent = PaymentFailedEvent
                .builder()
                .paymentId(command.getPaymentId())
                .orderId(command.getOrderId())
                .paymentStatus(command.getPaymentStatus())
                .build();
        AggregateLifecycle.apply(paymentFailedEvent);
        log.info("PaymentFailedEvent Applied");
    }

    @EventSourcingHandler
    public void on(PaymentFailedEvent event) {
        this.paymentStatus = event.getPaymentStatus();
    }
}

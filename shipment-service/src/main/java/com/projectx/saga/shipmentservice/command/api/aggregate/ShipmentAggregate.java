package com.projectx.saga.shipmentservice.command.api.aggregate;

import com.projectx.saga.commonservice.command.ShipOrderCommand;
import com.projectx.saga.commonservice.command.ShipmentInCompleteCommand;
import com.projectx.saga.commonservice.events.ShipOrderEvent;
import com.projectx.saga.commonservice.events.ShipmentInCompleteEvent;
import com.projectx.saga.commonservice.utils.CommonUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShipmentAggregate {
    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String paymentId;
    private String shipmentStatus;

    public ShipmentAggregate() {

    }

    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand command) {
        ShipOrderEvent shipOrderEvent = ShipOrderEvent.builder()
                .shipmentId(command.getShipmentId())
                .orderId(command.getOrderId())
                .paymentId(command.getPaymentId())
                .shipmentStatus(CommonUtils.SHIPMENT_STATUS)
                .build();
        AggregateLifecycle.apply(shipOrderEvent);
    }

    @EventSourcingHandler
    public void on(ShipOrderEvent event) {
        this.shipmentId = event.getShipmentId();
        this.orderId = event.getOrderId();
        this.paymentId = event.getPaymentId();
        this.shipmentStatus = event.getShipmentStatus();
    }

    @CommandHandler
    public ShipmentAggregate(ShipmentInCompleteCommand command) {
        ShipmentInCompleteEvent shipmentInCompleteEvent = ShipmentInCompleteEvent.builder()
                .shipmentId(command.getShipmentId())
                .orderId(command.getOrderId())
                .paymentId(command.getPaymentId())
                .shipmentStatus(command.getShipmentStatus())
                .build();
        AggregateLifecycle.apply(shipmentInCompleteEvent);
    }

    @EventSourcingHandler
    public void on(ShipmentInCompleteEvent event) {
        this.shipmentId = event.getShipmentId();
        this.orderId = event.getOrderId();
        this.paymentId = event.getPaymentId();
        this.shipmentStatus=event.getShipmentStatus();
    }
}

package com.projectx.saga.shipmentservice.command.api.event;

import com.projectx.saga.commonservice.events.ShipOrderEvent;
import com.projectx.saga.commonservice.events.ShipmentInCompleteEvent;
import com.projectx.saga.shipmentservice.command.api.data.ShipmentDetails;
import com.projectx.saga.shipmentservice.command.api.data.ShipmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ShipOrderEventHandler {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @EventHandler
    public void on(ShipOrderEvent event) {
        ShipmentDetails shipmentDetails = ShipmentDetails.builder()
                .shipmentId(event.getShipmentId())
                .orderId(event.getOrderId())
                .shipmentDate(new Date())
                .shipmentStatus(event.getShipmentStatus())
                .build();
        shipmentRepository.save(shipmentDetails);
    }

    @EventHandler
    public void on(ShipmentInCompleteEvent event) {
        ShipmentDetails shipmentDetails = shipmentRepository.findById(event.getShipmentId()).get();
        shipmentDetails.setShipmentStatus(event.getShipmentStatus());
        shipmentRepository.save(shipmentDetails);
    }
}

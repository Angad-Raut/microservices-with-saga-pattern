package com.projectx.saga.commonservice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentInCompleteCommand {
    @TargetAggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String paymentId;
    private String shipmentStatus;
}

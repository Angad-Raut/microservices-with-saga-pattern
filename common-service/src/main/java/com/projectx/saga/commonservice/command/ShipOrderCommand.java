package com.projectx.saga.commonservice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipOrderCommand {
    @TargetAggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String paymentId;
}

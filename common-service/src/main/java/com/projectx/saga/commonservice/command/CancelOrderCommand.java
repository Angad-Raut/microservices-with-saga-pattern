package com.projectx.saga.commonservice.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private String orderStatus;
}

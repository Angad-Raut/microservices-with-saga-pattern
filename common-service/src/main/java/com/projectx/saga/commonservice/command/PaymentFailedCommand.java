package com.projectx.saga.commonservice.command;

import com.projectx.saga.commonservice.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentFailedCommand {
    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;
}

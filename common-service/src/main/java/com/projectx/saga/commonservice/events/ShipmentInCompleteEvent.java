package com.projectx.saga.commonservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentInCompleteEvent {
    private String shipmentId;
    private String orderId;
    private String paymentId;
    private String shipmentStatus;
}

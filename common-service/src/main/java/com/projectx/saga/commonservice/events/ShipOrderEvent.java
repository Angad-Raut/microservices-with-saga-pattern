package com.projectx.saga.commonservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipOrderEvent {
    private String shipmentId;
    private String orderId;
    private String paymentId;
    private String shipmentStatus;
}

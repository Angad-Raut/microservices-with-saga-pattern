package com.projectx.saga.shipmentservice.command.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_shipment")
public class ShipmentDetails {
    @Id
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;
    private Date shipmentDate;
}

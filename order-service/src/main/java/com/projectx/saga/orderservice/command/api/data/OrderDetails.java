package com.projectx.saga.orderservice.command.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    private String orderId;
    private String userId;
    private String orderStatus;
    private Double orderAmount;
    private Date orderDate;
    private String productId;
    private Double productPrice;
    private Integer productQty;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private OrderAddress orderAddress;
}

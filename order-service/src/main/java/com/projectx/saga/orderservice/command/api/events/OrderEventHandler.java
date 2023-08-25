package com.projectx.saga.orderservice.command.api.events;

import com.projectx.saga.commonservice.dto.Address;
import com.projectx.saga.commonservice.events.CancelOrderEvent;
import com.projectx.saga.commonservice.queries.GetUserAddressByUserIdQuery;
import com.projectx.saga.orderservice.command.api.data.OrderAddress;
import com.projectx.saga.orderservice.command.api.data.OrderDetails;
import com.projectx.saga.orderservice.command.api.data.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class OrderEventHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private transient QueryGateway queryGateway;

    @EventHandler
    public void on(CreateOrderEvent event) {
        Double totalAmt = (event.getProductPrice()*event.getProductQty());
        OrderDetails orderDetails = OrderDetails.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .orderDate(new Date())
                .orderAmount(totalAmt)
                .orderStatus(event.getOrderStatus())
                .productId(event.getProductId())
                .productPrice(event.getProductPrice())
                .productQty(event.getProductQty())
                .orderAddress(toAddress(event.getUserId()))
                .build();
        orderRepository.save(orderDetails);
    }
    private OrderAddress toAddress(String userId) {
        GetUserAddressByUserIdQuery userAddressByUserIdQuery = new
                GetUserAddressByUserIdQuery(userId);
        Address address = queryGateway.query(
                userAddressByUserIdQuery,
                ResponseTypes.instanceOf(Address.class)
        ).join();
        return address!=null?OrderAddress.builder()
                .city(address.getCity()!=null?address.getCity():null)
                .country(address.getCountry()!=null?address.getCountry():null)
                .state(address.getState()!=null?address.getState():null)
                .street(address.getStreet()!=null?address.getStreet():null)
                .pinCode(address.getPinCode()!=null?address.getPinCode():null)
                .build() : new OrderAddress();
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        OrderDetails orderDetails = orderRepository.findById(event.getOrderId()).get();
        orderDetails.setOrderStatus(event.getOrderStatus());
        orderRepository.save(orderDetails);
    }

    @EventHandler
    public void on(CancelOrderEvent event) {
        OrderDetails orderDetails = orderRepository.findById(event.getOrderId()).get();
        orderDetails.setOrderStatus(event.getOrderStatus());
        orderRepository.save(orderDetails);
    }
}

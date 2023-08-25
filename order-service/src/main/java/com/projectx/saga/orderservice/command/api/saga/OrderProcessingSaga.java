package com.projectx.saga.orderservice.command.api.saga;

import com.projectx.saga.commonservice.command.*;
import com.projectx.saga.commonservice.dto.ProductInfo;
import com.projectx.saga.commonservice.dto.User;
import com.projectx.saga.commonservice.events.*;
import com.projectx.saga.commonservice.queries.GetUserPaymentDetailsQuery;
import com.projectx.saga.commonservice.utils.CommonUtils;
import com.projectx.saga.orderservice.command.api.events.CreateOrderEvent;
import com.projectx.saga.orderservice.command.api.events.OrderCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Saga
@Slf4j
public class OrderProcessingSaga {

    @Autowired
    private transient QueryGateway queryGateway;
    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handleOrderCreateEvent(CreateOrderEvent event) throws ExecutionException, InterruptedException {
        log.info("CreateOrderEvent in saga for order id : {}",event.getOrderId());
        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery(event.getUserId());
        User user = null;
        List<ProductInfo> productInfoList = null;
        try {
            user = queryGateway.query(
                    getUserPaymentDetailsQuery,
                    ResponseTypes.instanceOf(User.class)
            ).join();
        } catch (Exception e) {
            log.error(e.getMessage());
            //Handle compensation for (cancel order)
            cancelOrderCommand(event.getOrderId());
        }
        if (user!=null) {
            ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
                    .cardDetails(user.getCardDetails())
                    .orderId(event.getOrderId())
                    .paymentId(UUID.randomUUID().toString())
                    .build();
            commandGateway.sendAndWait(validatePaymentCommand);
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handleOrderPaymentEvent(PaymentProcessedEvent event) {
        log.info("PaymentProcessedEvent in saga for orderId : {}",event.getOrderId());
        try {
            ShipOrderCommand shipOrderCommand = ShipOrderCommand.builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .paymentId(event.getPaymentId())
                    .build();
            commandGateway.sendAndWait(shipOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            //Handle compensation for (cancel payment)
            cancelOrderPaymentCommand(event);
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handleOrderShipmentEvent(ShipOrderEvent event) {
        log.info("ShipOrderEvent in saga for orderId : {}",event.getOrderId());
        try {
            CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                    .orderId(event.getOrderId())
                    .orderStatus(CommonUtils.ORDER_APPROVED_STATUS)
                    .build();
            commandGateway.send(completeOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            //Handle compensation for (cancel shipment)
            cancelOrderShipmentCommand(event);
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    private void handleOrderCompleteEvent(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in saga for orderId : {}",event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handleCancelOrderEvent(CancelOrderEvent event) {
        log.info("CancelOrderEvent in saga for orderId : {}",event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handleCancelPaymentEvent(PaymentFailedEvent event) {
        log.info("PaymentFailedEvent in saga for orderId : {}",event.getOrderId());
        cancelOrderCommand(event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handleCancelShipmentEvent(ShipmentInCompleteEvent event) {
        log.info("PaymentFailedEvent in saga for orderId : {}",event.getOrderId());
        cancelOrderPaymentCommandAfterShipmentFailed(event.getOrderId(),event.getPaymentId());
    }


    private void cancelOrderPaymentCommand(PaymentProcessedEvent event) {
        PaymentFailedCommand paymentFailedCommand = PaymentFailedCommand.builder()
                .orderId(event.getOrderId())
                .paymentId(event.getPaymentId())
                .paymentStatus(CommonUtils.PAYMENT_FAILED_STATUS)
                .build();
        commandGateway.send(paymentFailedCommand);
    }

    private void cancelOrderPaymentCommandAfterShipmentFailed(String orderId,String paymentId) {
        PaymentFailedCommand paymentFailedCommand = PaymentFailedCommand.builder()
                .orderId(orderId)
                .paymentId(paymentId)
                .paymentStatus(CommonUtils.PAYMENT_FAILED_STATUS)
                .build();
        commandGateway.send(paymentFailedCommand);
    }

    private void cancelOrderShipmentCommand(ShipOrderEvent event) {
        ShipmentInCompleteCommand shipmentInCompleteCommand = ShipmentInCompleteCommand
                .builder()
                .orderId(event.getOrderId())
                .paymentId(event.getPaymentId())
                .shipmentId(event.getShipmentId())
                .shipmentStatus(CommonUtils.SHIPMENT_INCOMPLETE_STATUS)
                .build();
        commandGateway.send(shipmentInCompleteCommand);
    }

    public void cancelOrderCommand(String orderId) {
        CancelOrderCommand cancelOrderCommand = CancelOrderCommand
                .builder()
                .orderId(orderId)
                .orderStatus(CommonUtils.ORDER_CANCELLED_STATUS)
                .build();
        commandGateway.send(cancelOrderCommand);
    }
}

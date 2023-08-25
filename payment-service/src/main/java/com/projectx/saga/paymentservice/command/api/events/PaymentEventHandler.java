package com.projectx.saga.paymentservice.command.api.events;

import com.projectx.saga.commonservice.events.PaymentFailedEvent;
import com.projectx.saga.commonservice.events.PaymentProcessedEvent;
import com.projectx.saga.commonservice.utils.CommonUtils;
import com.projectx.saga.paymentservice.command.api.data.Payment;
import com.projectx.saga.paymentservice.command.api.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentEventHandler {

    @Autowired
    private PaymentRepository paymentRepository;

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        Payment payment = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus(CommonUtils.PAYMENT_STATUS)
                .paymentDate(new Date())
                .build();
        paymentRepository.save(payment);
    }

    @EventHandler
    public void on(PaymentFailedEvent event) {
        Payment payment = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus(event.getPaymentStatus())
                .build();
        paymentRepository.save(payment);
    }
}

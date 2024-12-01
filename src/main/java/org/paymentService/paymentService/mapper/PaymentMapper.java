package org.paymentService.paymentService.mapper;

import org.paymentService.paymentService.models.Payment;
import org.paymentService.paymentService.dto.PaymentDto;

public class PaymentMapper {

    public static PaymentDto map(Payment payment){
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .isPayed(payment.getIsPayed())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())


                .build();

    }
    public static Payment map(PaymentDto paymentDto){
        return Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .orderId(paymentDto.getOrderId())
                .isPayed(paymentDto.getIsPayed())
                .paymentMethod(paymentDto.getPaymentMethod())
                .paymentStatus(paymentDto.getPaymentStatus())
                .build();
    }
}
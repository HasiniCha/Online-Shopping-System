package org.paymentService.paymentService.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentMethod{

    ON_DELIVERY("on_delivery"),
    CARD("card");

    private final String method;

}

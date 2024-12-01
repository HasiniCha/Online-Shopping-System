package org.paymentService.paymentService.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    PENDING("pending"),
    PAYED("payed");
     private final String status;
}

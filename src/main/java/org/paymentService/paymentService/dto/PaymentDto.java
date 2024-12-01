package org.paymentService.paymentService.dto;

import org.paymentService.paymentService.models.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.paymentService.paymentService.models.PaymentStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto{

    private Long paymentId;
    private Boolean isPayed;
    private PaymentMethod paymentMethod;
    private Long orderId;
    private PaymentStatus paymentStatus;



//    @JsonProperty("order")
//    @JsonInclude(Include.NON_NULL)
//    private OrderDto orderDto;


}


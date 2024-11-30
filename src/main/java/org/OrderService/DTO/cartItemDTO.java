package org.OrderService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class cartItemDTO {
    private Long id;
    private String productId;
    private Long cartId;
    private double productPrice;
    private int quantity;
    private double subTotal;


}
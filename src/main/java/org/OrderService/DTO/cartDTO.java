package org.OrderService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class cartDTO {
    private Long id;
    private String customerId;
    private Double totalAmount;
    private List<cartItemDTO> cartItems;
}

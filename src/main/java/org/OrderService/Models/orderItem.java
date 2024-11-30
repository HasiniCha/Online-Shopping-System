package org.OrderService.Models;
import org.OrderService.Models.orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_Items")
public class orderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private orders orders;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    @PrePersist
    public void calculateTotalPrice() {
        if (this.productPrice > 0 && this.quantity > 0) {
            this.totalPrice = this.productPrice * this.quantity;
        } else {
            this.totalPrice = 0.0;
        }

    }
}

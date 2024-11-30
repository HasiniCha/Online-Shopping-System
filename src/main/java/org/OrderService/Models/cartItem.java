package org.OrderService.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class cartItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;



        @Column(name = "product_id")
        private String productId;

        @Column(name = "product_price")
        private double productPrice;

        @Column(name = "quantity")
        private int quantity;

        @Column(name = "sub_total")
        private double subTotal;

        @ManyToOne
        @JoinColumn(name = "cart_id")
        @JsonBackReference
        private cart cart;


        @PrePersist
        @PreUpdate
        public void calculateSubTotal() {
                if (this.productPrice > 0 && this.quantity > 0) {
                        this.subTotal = this.productPrice * this.quantity;
                } else {
                        this.subTotal = 0.0;
                }
        }


}

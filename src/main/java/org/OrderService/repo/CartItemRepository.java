package org.OrderService.repo;

import org.OrderService.Models.cartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<cartItem, Long> {
    void deleteAllByCartId(Long cart_id);


}
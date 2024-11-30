package org.OrderService.repo;
import org.OrderService.Models.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepository extends JpaRepository<cart,Long> {

}

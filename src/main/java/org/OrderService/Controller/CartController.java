package org.OrderService.Controller;


import org.OrderService.DTO.cartDTO;
import org.OrderService.Models.cart;
import org.OrderService.Services.CartService;
import org.OrderService.repo.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    private CartItemRepository CartItemRepository;



    @Autowired
    public CartController(CartItemRepository cartItemRepository) {
        this.CartItemRepository = cartItemRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<cartDTO> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @PostMapping
    public ResponseEntity<cart> createCart(@RequestBody cart cart) {
        return ResponseEntity.ok(cartService.saveCart(cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/items")
    public ResponseEntity<Void> deleteAllItemsInCart(@PathVariable Long id) {
        cartService.deleteAllItemsInCart(id);
        CartItemRepository.deleteAllByCartId(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long id) {
        double total = cartService.calculateCartTotal(id);
        return ResponseEntity.ok(total);
    }
}

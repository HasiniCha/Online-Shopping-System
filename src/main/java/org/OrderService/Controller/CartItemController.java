package org.OrderService.Controller;

import org.OrderService.DTO.cartItemDTO;
import org.OrderService.Models.cartItem;
import org.OrderService.Services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;


    @PostMapping
    public ResponseEntity<cartItem> addCartItem(@RequestBody cartItemDTO cartItemDTO) {
        cartItem newCartItem = cartItemService.addCartItem(cartItemDTO);

        return ResponseEntity.ok(newCartItem);
    }


    @GetMapping
    public ResponseEntity<List<cartItem>> getAllCartItems() {
        List<cartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<cartItem> getCartItemById(@PathVariable Long id) {
        cartItem cartItem = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}

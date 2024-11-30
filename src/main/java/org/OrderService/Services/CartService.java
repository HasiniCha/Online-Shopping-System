package org.OrderService.Services;

import org.OrderService.DTO.cartDTO;
import org.OrderService.DTO.cartItemDTO;
import org.OrderService.Models.cart;
import org.OrderService.repo.CartItemRepository;
import org.OrderService.repo.CartRepository;
import org.OrderService.Models.cartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public cartDTO getCartById(Long id) {
        Optional<cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }

        cart cart = optionalCart.get();
        cartDTO cartDTO = new cartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setCustomerId(cart.getCustomerId());
        cartDTO.setTotalAmount(cart.getTotalAmount());
        cartDTO.setCartItems(cart.getCartItems().stream().map(item -> {
            cartItemDTO itemDTO = new cartItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setProductId(item.getProductId());
            itemDTO.setProductPrice(item.getProductPrice());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setSubTotal(item.getSubTotal());
            return itemDTO;
        }).toList());

        return cartDTO;
    }

    public cart saveCart(cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void deleteAllItemsInCart(Long id) {
        cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    public double calculateCartTotal(Long cartId) {
        cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<cartItem> items = cart.getCartItems();
        double total = 0;
        for (cartItem item : items) {
            total += item.getSubTotal() ;
        }
        return total;

}}


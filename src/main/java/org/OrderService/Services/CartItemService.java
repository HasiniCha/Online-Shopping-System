package org.OrderService.Services;

import org.OrderService.Models.cart;
import org.OrderService.Models.cartItem;
import org.OrderService.DTO.cartItemDTO;
import org.OrderService.repo.CartItemRepository;
import org.OrderService.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    public cartItem addCartItem(cartItemDTO cartItemDTO) {

        cart cart = cartRepository.findById(cartItemDTO.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));


        cartItem newCartItem = new cartItem();
        newCartItem.setProductId(cartItemDTO.getProductId());
        newCartItem.setProductPrice(cartItemDTO.getProductPrice());
        newCartItem.setQuantity(cartItemDTO.getQuantity());
        newCartItem.setSubTotal(cartItemDTO.getSubTotal());
        newCartItem.setCart(cart);
        return cartItemRepository.save(newCartItem);
    }

    public List<cartItem> getAllCartItems() {


        return cartItemRepository.findAll();
    }

    public cartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart item not found"));
    }
    public void deleteCartItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new RuntimeException("Cart item not found");
        }
        cartItemRepository.deleteById(id);
    }
}

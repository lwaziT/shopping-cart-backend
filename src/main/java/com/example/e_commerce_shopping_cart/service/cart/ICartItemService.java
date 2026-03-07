package com.example.e_commerce_shopping_cart.service.cart;

import com.example.e_commerce_shopping_cart.model.CartItem;

public interface ICartItemService {
    void addCartItem(Long cartId, Long productId,int quantity);
    void removeCartItem(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}

package com.example.e_commerce_shopping_cart.repository;

import com.example.e_commerce_shopping_cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long cartId);
}

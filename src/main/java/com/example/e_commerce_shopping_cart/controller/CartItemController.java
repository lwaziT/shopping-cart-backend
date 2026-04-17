package com.example.e_commerce_shopping_cart.controller;

import com.example.e_commerce_shopping_cart.exceptions.ResourceNotFoundException;
import com.example.e_commerce_shopping_cart.model.Cart;
import com.example.e_commerce_shopping_cart.model.User;
import com.example.e_commerce_shopping_cart.response.ApiResponse;
import com.example.e_commerce_shopping_cart.service.cart.ICartItemService;
import com.example.e_commerce_shopping_cart.service.cart.ICartService;
import com.example.e_commerce_shopping_cart.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping("/add-item")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long itemId, @RequestParam Integer quantity) {
        try {
            User user = userService.getUserById(2L);
            Cart cart = cartService.initializeNewCart(user);

            cartItemService.addCartItem(cart.getId(), itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Add item success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/remove-item/cart/{cartId}/item/{itemId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeCartItem(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Remove item success", null));
        }  catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update-item-quantity/cart/{cartId}/item/{itemId}")
    public  ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Update item quantity success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
























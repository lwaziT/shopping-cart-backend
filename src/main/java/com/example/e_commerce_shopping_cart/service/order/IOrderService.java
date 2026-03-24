package com.example.e_commerce_shopping_cart.service.order;

import com.example.e_commerce_shopping_cart.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}

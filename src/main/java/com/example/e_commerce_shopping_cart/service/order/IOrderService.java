package com.example.e_commerce_shopping_cart.service.order;

import com.example.e_commerce_shopping_cart.dto.OrderDto;
import com.example.e_commerce_shopping_cart.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}

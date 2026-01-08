package com.example.e_commerce_shopping_cart.repository;

import com.example.e_commerce_shopping_cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

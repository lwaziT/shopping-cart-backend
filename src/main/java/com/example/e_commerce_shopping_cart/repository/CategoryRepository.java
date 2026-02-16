package com.example.e_commerce_shopping_cart.repository;

import com.example.e_commerce_shopping_cart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}

package com.example.e_commerce_shopping_cart.service.product;

import com.example.e_commerce_shopping_cart.model.Category;
import com.example.e_commerce_shopping_cart.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);Product getProductById(int id);
    List<Product> getProducts();
    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(Category category, String brand);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product, Long productId);
    Long countProductsByBrandAndName(String brand, String name);
}

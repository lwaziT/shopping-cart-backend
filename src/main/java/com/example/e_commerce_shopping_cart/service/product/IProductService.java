package com.example.e_commerce_shopping_cart.service.product;

import com.example.e_commerce_shopping_cart.model.Category;
import com.example.e_commerce_shopping_cart.model.Product;
import com.example.e_commerce_shopping_cart.request.AddProductRequest;
import com.example.e_commerce_shopping_cart.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    List<Product> getProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getProductsByCategoryAndBrand(Category category, String brand);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    Long countProductsByBrandAndName(String brand, String name);
}

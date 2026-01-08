package com.example.e_commerce_shopping_cart.service.product;

import com.example.e_commerce_shopping_cart.model.Category;
import com.example.e_commerce_shopping_cart.model.Product;
import com.example.e_commerce_shopping_cart.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(Category category, String brand) {
        return List.of();
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return 0L;
    }
}

package com.example.e_commerce_shopping_cart.service.product;

import com.example.e_commerce_shopping_cart.exceptions.ProductNotFoundException;
import com.example.e_commerce_shopping_cart.model.Category;
import com.example.e_commerce_shopping_cart.model.Product;
import com.example.e_commerce_shopping_cart.repository.ProductRepository;
import com.example.e_commerce_shopping_cart.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(AddProductRequest request) {

        return null;
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {

        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {

        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {

        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {

        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(Category category, String brand) {

        return productRepository.findByCategoryNameAndBrand(category, brand);
    }


    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                ()->{throw new ProductNotFoundException("Product not found");});
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}

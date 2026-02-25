package com.example.e_commerce_shopping_cart.controller;

import com.example.e_commerce_shopping_cart.dto.ProductDTO;
import com.example.e_commerce_shopping_cart.exceptions.ResourceNotFoundException;
import com.example.e_commerce_shopping_cart.model.Product;
import com.example.e_commerce_shopping_cart.request.AddProductRequest;
import com.example.e_commerce_shopping_cart.request.ProductUpdateRequest;
import com.example.e_commerce_shopping_cart.response.ApiResponse;
import com.example.e_commerce_shopping_cart.service.product.IProductService;
import com.example.e_commerce_shopping_cart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getProducts();
        List<ProductDTO> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
        try {
            Product product = productService.getProductById(id);
            ProductDTO productDTO = productService.convertToDTO(product);
            return ResponseEntity.ok(new ApiResponse("Success", productDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name){
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Success", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            List<ProductDTO> convertedProducts = productService.getConvertedProducts(products);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByBrandAndName(category, brand);
            List<ProductDTO> convertedProducts = productService.getConvertedProducts(products);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by-name")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name){
        try {
            List<Product> products = productService.getProductsByName(name);
            List<ProductDTO> convertedProducts = productService.getConvertedProducts(products);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            List<ProductDTO> convertedProducts = productService.getConvertedProducts(products);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/category/{category}")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String category){
        try {
            List<Product> products = productService.getProductsByCategory(category);
            List<ProductDTO> convertedProducts = productService.getConvertedProducts(products);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Success", newProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long id){
        try {
            Product product = productService.updateProduct(request,id);
            return ResponseEntity.ok(new ApiResponse("Success", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }































}

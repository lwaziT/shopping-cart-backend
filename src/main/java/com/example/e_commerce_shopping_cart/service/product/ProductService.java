package com.example.e_commerce_shopping_cart.service.product;

import com.example.e_commerce_shopping_cart.dto.ImageDTO;
import com.example.e_commerce_shopping_cart.dto.ProductDTO;
import com.example.e_commerce_shopping_cart.exceptions.ProductNotFoundException;
import com.example.e_commerce_shopping_cart.model.Category;
import com.example.e_commerce_shopping_cart.model.Image;
import com.example.e_commerce_shopping_cart.model.Product;
import com.example.e_commerce_shopping_cart.repository.CategoryRepository;
import com.example.e_commerce_shopping_cart.repository.ImageRepository;
import com.example.e_commerce_shopping_cart.repository.ProductRepository;
import com.example.e_commerce_shopping_cart.request.AddProductRequest;
import com.example.e_commerce_shopping_cart.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product addProduct(AddProductRequest request) {
        /*
          Check if category is found in the DataBase
          If Yes, set as the new product category
          If No, save the new product category
         */
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
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
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository :: save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));

    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDTO> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDTO).toList();
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDTO> imageDTOS = images.stream().map(image -> modelMapper.map(image, ImageDTO.class)).toList();
        productDTO.setImages(imageDTOS);
        return productDTO;
    }






























}

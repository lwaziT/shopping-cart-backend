package com.example.e_commerce_shopping_cart.service.image;

import com.example.e_commerce_shopping_cart.dto.ImageDTO;
import com.example.e_commerce_shopping_cart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    List<ImageDTO> saveImages(List<MultipartFile> multipartFile, Long productId);
    void updateImage(MultipartFile multipartFile, Long ImageId);
    void deleteImageById(Long id);
}

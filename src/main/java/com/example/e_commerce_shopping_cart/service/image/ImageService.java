package com.example.e_commerce_shopping_cart.service.image;

import com.example.e_commerce_shopping_cart.dto.ImageDTO;
import com.example.e_commerce_shopping_cart.exceptions.ResourceNotFoundException;
import com.example.e_commerce_shopping_cart.model.Image;
import com.example.e_commerce_shopping_cart.model.Product;
import com.example.e_commerce_shopping_cart.repository.ImageRepository;
import com.example.e_commerce_shopping_cart.repository.ProductRepository;
import com.example.e_commerce_shopping_cart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No image found with this id: " + id));
    }

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);

        List<ImageDTO> imageDTOS = new ArrayList<>();

        for (MultipartFile file : files) {
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String downloadUrl = "/api/v1/images/image/download/" + image.getId();

                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl("/api/v1/images/image/download/" + savedImage.getId());

                imageRepository.save(savedImage);

                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageId(savedImage.getId());
                imageDTO.setImageName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                imageDTOS.add(imageDTO);

            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return imageDTOS;
    }

    @Override
    public void updateImage(MultipartFile file, Long ImageId) {
        Image image = getImageById(ImageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException |SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("No image found with this id: " + id);
        });
    }
}

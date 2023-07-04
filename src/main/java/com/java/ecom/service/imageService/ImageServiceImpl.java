package com.java.ecom.service.imageService;

import com.java.ecom.entity.Image;
import com.java.ecom.entity.Product;
import com.java.ecom.exception.AppRuntimeException;
import com.java.ecom.repository.ImageRepository;
import com.java.ecom.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements IimageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private IProductService productService;

    @Override
    public Image createImage(String productId, Image image) {
        Product product = productService.getProductById(productId);
        Image save = null;
        try {
            image.setProduct(product);
            save = imageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }

    @Override
    public Image getImageById(String productId,String imageId) {
        Optional<Image> optionalImage = imageRepository.findById(imageId);
        try {
            if (optionalImage.isPresent()) {
                return optionalImage.get();
            }
            throw new AppRuntimeException("Image not present");
        } catch (Exception e) {
                throw new AppRuntimeException(e, "Error while getting image");
        }
    }

    @Override
    public List<Image> getImages() {
        List<Image> all = null;
        try {
            all = imageRepository.findAll();
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());

        }
        return all;
    }

    @Override
    public Image updateImage(Image image,String productId) {
        Image existingProduct = imageRepository.findById(image.getId()).get();
        existingProduct.setId(image.getId());
        existingProduct.setImageLink(image.getImageLink());
        existingProduct.setSequence(image.getSequence());
        Product product=productService.getProductById(productId);
        Image updatedroduct = null;
        try {
            image.setProduct(product);
            updatedroduct = imageRepository.save(existingProduct);
        } catch (RuntimeException e) {
            throw e;
        }
        return updatedroduct;
    }

    @Override
    public void deleteImage(String productId,String imageId) {
        Image image=new Image();
       Product product= productService.getProductById(productId);
        try {
//            image.setProduct(product);
            imageRepository.deleteById(imageId);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
    }
}


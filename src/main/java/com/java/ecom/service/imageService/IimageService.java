package com.java.ecom.service.imageService;

import com.java.ecom.entity.Image;

import java.util.List;

public interface IimageService {
    Image createImage(String productId,Image image);

    Image getImageById(String productId,String imageId);

    List<Image> getImages();

    Image updateImage(Image Image,String productId);

    void deleteImage(String productId,String imageId);
}

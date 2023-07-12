package com.java.ecom.controller;

import com.java.ecom.entity.Image;
import com.java.ecom.service.imageService.IimageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/products")
public class ImageController {
    @Autowired
    private IimageService imageService;

    @PostMapping("/{productId}/images")
    public ResponseEntity<Image> createImage(@PathVariable String productId, @RequestBody Image image) {
        Image savedUser = imageService.createImage(productId, image);
        ResponseEntity<Image> imageResponseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        return imageResponseEntity;
    }

    @GetMapping("/{productId}/images/{imageId}")
    public ResponseEntity<Image> getImageById(@PathVariable("productId") String productId,@PathVariable("imageId") String imageId) {
        Image image = imageService.getImageById(productId,imageId);
        if (null!=image.getId()) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getImages();
        ResponseEntity<List<Image>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(images, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("/{productId}/images/{imageId}")
    public ResponseEntity<Image> updateImage(@RequestBody Image image,@PathVariable("productId") String productId,@PathVariable("imageId") String imageId) {
        image.setId(imageId);
        Image updatedImage = imageService.updateImage(image,productId);
        ResponseEntity<Image> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(updatedImage, HttpStatus.OK);
        return productResponseEntity;
    }

    @DeleteMapping("/{productId}/images/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("productId") String productId,@PathVariable("imageId") String imageId) {
        imageService.deleteImage(productId,imageId);
        ResponseEntity<String> stringResponseEntity = null;
        stringResponseEntity = new ResponseEntity<>("Image successfully deleted!", HttpStatus.OK);
        return stringResponseEntity;
    }
}

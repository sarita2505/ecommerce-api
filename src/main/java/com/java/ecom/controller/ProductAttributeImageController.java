package com.java.ecom.controller;

import com.java.ecom.entity.Image;
import com.java.ecom.entity.ProductAttributeImage;
import com.java.ecom.service.productAttributeImageService.IProductAttributeImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/products")
public class ProductAttributeImageController {
    @Autowired
    private IProductAttributeImageService attributeImageService;

    @PostMapping("/{prodAttributeId}/prodAttributeimages")
    public ResponseEntity<ProductAttributeImage> createProdAttributeImage(@PathVariable String prodAttributeId, @RequestBody ProductAttributeImage image) {
        ProductAttributeImage savedUser = attributeImageService.createProdAttributeImage(prodAttributeId, image);
        ResponseEntity<ProductAttributeImage> imageResponseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        return imageResponseEntity;
    }

    @GetMapping("/{prodAttributeId}/prodAttributeimages/{prodAttributeimageId}")
    public ResponseEntity<ProductAttributeImage> getProductAttributeImageById(@PathVariable("prodAttributeId") String prodAttributeId,@PathVariable("prodAttributeimageId") String prodAttributeimageId) {
        ProductAttributeImage image = attributeImageService.getProdAttributeImageById(prodAttributeId,prodAttributeimageId);
        if (null!=image.getId()) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{prodAttributeId}/prodAttributeimages")
    public ResponseEntity<List<ProductAttributeImage>> getProductAttributeImages(@PathVariable("prodAttributeId") String prodAttributeId) {
        List<ProductAttributeImage> images = attributeImageService.getProdAttributeImages(prodAttributeId);
        ResponseEntity<List<ProductAttributeImage>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(images, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("/{prodAttributeId}/prodAttributeimages/{ProductAttributeImageId}")
    public ResponseEntity<ProductAttributeImage> updateProductAttributeImages(@RequestBody ProductAttributeImage image,@PathVariable("prodAttributeId") String prodAttributeId,@PathVariable("ProductAttributeImageId") String ProductAttributeImageId) {
        image.setId(ProductAttributeImageId);
        ProductAttributeImage updatedImage = attributeImageService.updateProdAttributeImage(image,prodAttributeId);
        ResponseEntity<ProductAttributeImage> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(updatedImage, HttpStatus.OK);
        return productResponseEntity;
    }


    @DeleteMapping("/{prodAttributeId}/prodAttributeimages/{productAttributeImageId}")
    public ResponseEntity<String> deleteProductAttributeImage(@PathVariable("prodAttributeId") String prodAttributeId,@PathVariable("productAttributeImageId") String productAttributeImageId) {
        attributeImageService.deleteProdAttributeImage(prodAttributeId,productAttributeImageId);
        ResponseEntity<String> stringResponseEntity = null;
        stringResponseEntity = new ResponseEntity<>("Image successfully deleted!", HttpStatus.OK);
        return stringResponseEntity;
    }
}

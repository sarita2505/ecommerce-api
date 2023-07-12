package com.java.ecom.service.productAttributeImageService;

import com.java.ecom.entity.Image;
import com.java.ecom.entity.ProductAttributeImage;

import java.util.List;

public interface IProductAttributeImageService {
    ProductAttributeImage createProdAttributeImage(String productAttributeId,ProductAttributeImage image);

    ProductAttributeImage getProdAttributeImageById(String productAttributeId, String imageId);

    List<ProductAttributeImage> getProdAttributeImages(String productAttributeId);

    ProductAttributeImage updateProdAttributeImage(ProductAttributeImage Image,String productAttributeId);

    void deleteProdAttributeImage(String productAttributeId,String imageId);
}

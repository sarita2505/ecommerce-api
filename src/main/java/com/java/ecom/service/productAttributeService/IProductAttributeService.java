package com.java.ecom.service.productAttributeService;

import com.java.ecom.entity.ProductAttribute;

import java.util.List;

public interface IProductAttributeService {
    ProductAttribute createProdAttribute(String productId, ProductAttribute prodAttribute);

    ProductAttribute getProdAttributeById(String attributeId);

    List<ProductAttribute> getAllAttributes();

    List<ProductAttribute> getAllAttributesOfProductByProdId(String productId);

    ProductAttribute updateProdAttribute(ProductAttribute productAttribute,String productId);

    void deleteProdAttribute(String productId,String attributeId);
}

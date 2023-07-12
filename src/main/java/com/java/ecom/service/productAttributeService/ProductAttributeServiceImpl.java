package com.java.ecom.service.productAttributeService;

import com.java.ecom.entity.Product;
import com.java.ecom.entity.ProductAttribute;
import com.java.ecom.exception.AppRuntimeException;
import com.java.ecom.repository.ProductAttributrRepository;
import com.java.ecom.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ProductAttributeServiceImpl implements IProductAttributeService {
//    @Autowired
    private ProductAttributrRepository productAttributrRepository;
//    @Autowired
    private IProductService productService;

    @Override
    public ProductAttribute createProdAttribute(String productId, ProductAttribute prodAttribute) {
        Product product = productService.getProductById(productId);
        ProductAttribute save = null;
        try {
            prodAttribute.setProduct(product);
            save = productAttributrRepository.save(prodAttribute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
}

    @Override
    public ProductAttribute getProdAttributeById(String attributeId) {
        Optional<ProductAttribute> optionalProductAttribute = productAttributrRepository.findById(attributeId);
        try {
            if (optionalProductAttribute.isPresent()) {
                return optionalProductAttribute.get();
            }
            throw new AppRuntimeException("attribute is not present");
        } catch (Exception e) {
            throw new AppRuntimeException(e, "Error while getting image for this id");
        }
    }

    @Override
    public List<ProductAttribute> getAllAttributes() {
        List<ProductAttribute> all = null;
        try {
            all = productAttributrRepository.findAll();
        } catch (Exception e) {
            throw new AppRuntimeException(e,"attribute list is not available");

        }
        return all;
    }

    @Override
    public List<ProductAttribute> getAllAttributesOfProductByProdId(String productId) {
        Product product = productService.getProductById(productId);
        ProductAttribute productAttribute=new ProductAttribute();
        List<ProductAttribute> all = null;
        try {
            productAttribute.setProduct(product);
            all = productAttributrRepository.findByProductId(productId);
        } catch (Exception e) {
            throw new AppRuntimeException(e,"product list is not available");

        }
        return all;
    }

    @Override
    public ProductAttribute updateProdAttribute(ProductAttribute productAttribute, String productId) {
        ProductAttribute productAttribute1 = productAttributrRepository.findById(productAttribute.getId()).get();
        productAttribute1.setId(productAttribute.getId());
        productAttribute1.setColor(productAttribute.getColor());
        productAttribute1.setSize(productAttribute.getSize());
        productAttribute1.setStock(productAttribute.getStock());
        productAttribute1.setPrice(productAttribute.getPrice());
        productAttribute1.setProductAttributeImages(productAttribute.getProductAttributeImages());

        ProductAttribute updateAttribute = null;
        try {
            updateAttribute = productAttributrRepository.save(productAttribute1);
        } catch (RuntimeException e) {
            throw e;
        }
        return updateAttribute;
    }

    @Override
    public void deleteProdAttribute(String productId, String attributeId) {
//        ProductAttribute productAttribute=new ProductAttribute();
//        Product product= productService.getProductById(productId);
        try {
//            image.setProduct(product);
            productAttributrRepository.deleteById(attributeId);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
    }
}

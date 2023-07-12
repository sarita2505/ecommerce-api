package com.java.ecom.service.productAttributeImageService;

import com.java.ecom.entity.ProductAttribute;
import com.java.ecom.entity.ProductAttributeImage;
import com.java.ecom.exception.AppRuntimeException;
import com.java.ecom.repository.ProductAttributeImageRepository;
import com.java.ecom.service.productAttributeService.IProductAttributeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductAttributeImageServiceImpl implements IProductAttributeImageService {
    @Autowired
    private ProductAttributeImageRepository productAttributeImageRepository;
//    @Autowired
    private IProductAttributeService productAttributeService;
    @Override
    public ProductAttributeImage createProdAttributeImage(String productAttributeId, ProductAttributeImage attributeImage) {
        ProductAttribute productAttribute = productAttributeService.getProdAttributeById(productAttributeId);
        ProductAttributeImage save = null;
        try {
            attributeImage.setAttribute(productAttribute);
            save = productAttributeImageRepository.save(attributeImage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }

    @Override
    public ProductAttributeImage getProdAttributeImageById(String productId,String imageId) {
        Optional<ProductAttributeImage> optionalImage = productAttributeImageRepository.findById(imageId);
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
    public List<ProductAttributeImage> getProdAttributeImages(String prodAttributeId){
        List<ProductAttributeImage> all = null;
        try {
            all = productAttributeImageRepository.findByAttributeId(prodAttributeId);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());

        }
        return all;
    }

    @Override
    public ProductAttributeImage updateProdAttributeImage(ProductAttributeImage image,String productAttributeId) {
        ProductAttributeImage existingProduct = productAttributeImageRepository.findById(image.getId()).get();
        existingProduct.setId(image.getId());
        existingProduct.setImageLink(image.getImageLink());
        existingProduct.setSequence(image.getSequence());
        ProductAttribute productAttribute= productAttributeService.getProdAttributeById(productAttributeId);
        ProductAttributeImage updateProduct = null;
        try {
            image.setAttribute(productAttribute);
            updateProduct = productAttributeImageRepository.save(existingProduct);
        } catch (RuntimeException e) {
            throw e;
        }
        return updateProduct;
    }

    @Override
    public void deleteProdAttributeImage(String productId,String productAttributeImageId) {
        ProductAttributeImage image=new ProductAttributeImage();
//       ProductAttribute productAttribute= productAttributeService.getProdAttributeById(productId);
        try {
//            image.setProduct(product);
            productAttributeImageRepository.deleteById(productAttributeImageId);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
    }
}


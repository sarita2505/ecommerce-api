package com.java.ecom.service;

import com.java.ecom.entity.Product;
import com.java.ecom.exception.ProductNotFoundException;
import com.java.ecom.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        Product save = null;
        try {
            save = productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }

    @Override
    public Product getProductById(String userId) {
        Optional<Product> optionalUser = productRepository.findById(userId);
        try {
            if (optionalUser.isPresent()) {
                return optionalUser.get();
            }
        } catch (Exception e) {
            throw new ProductNotFoundException(e.getCause().getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> all = null;
        try {
            all = productRepository.findAll();
        } catch (Exception e) {
            throw new ProductNotFoundException(e.getCause().getMessage());
        }
        return all;
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).get();
        existingProduct.setId(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        Product updatedroduct = null;
        try {
            updatedroduct = productRepository.save(existingProduct);
        } catch (Exception e) {
            throw new ProductNotFoundException(e.getCause().getMessage());
        }
        return updatedroduct;
    }

    @Override
    public void deleteProduct(String userId) {
        try {
            productRepository.deleteById(userId);
        } catch (Exception e) {
            throw new ProductNotFoundException(e.getCause().getMessage());
        }
    }
}


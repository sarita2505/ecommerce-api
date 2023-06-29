package com.java.ecom.controller;

import com.java.ecom.entity.Product;
import com.java.ecom.exception.ErrorResponse;
import com.java.ecom.exception.GlobalExceptionHandler;
import com.java.ecom.exception.ProductNotFoundException;
import com.java.ecom.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {
    private IProductService productService;

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ErrorResponse {
        Product savedUser = productService.createProduct(product);
        ResponseEntity<Product> productResponseEntity = null;
        try {
            productResponseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw globalExceptionHandler.handleException(e);
        }
        return productResponseEntity;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String userId) throws ErrorResponse {
        Product product = productService.getProductById(userId);
        try {
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw globalExceptionHandler.handleProductNotFoundException((ProductNotFoundException) e);
        }

        return null;
    }

    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() throws ErrorResponse {
        List<Product> products = productService.getProducts();
        ResponseEntity<List<Product>> listResponseEntity = null;
        try {
            listResponseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            throw globalExceptionHandler.handleProductNotFoundException((ProductNotFoundException) e);
        }
        return listResponseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String userId,
                                                 @RequestBody Product product) throws ErrorResponse {
        product.setId(userId);
        Product updatedProduct = productService.updateProduct(product);
        ResponseEntity<Product> productResponseEntity = null;
        try {
            productResponseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            throw globalExceptionHandler.handleProductNotFoundException((ProductNotFoundException) e);
        }
        return productResponseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String userId) throws ErrorResponse {
        productService.deleteProduct(userId);
        ResponseEntity<String> stringResponseEntity = null;
        try {
            stringResponseEntity = new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
        } catch (Exception e) {
            throw globalExceptionHandler.handleProductNotFoundException((ProductNotFoundException) e);
        }
        return stringResponseEntity;
    }
}

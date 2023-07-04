package com.java.ecom.controller;

import com.java.ecom.entity.Product;
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

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedUser = productService.createProduct(product);
        ResponseEntity<Product> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        return productResponseEntity;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String userId) {
        Product product = productService.getProductById(userId);
        if (product.getId() != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        return null;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getProducts();
        ResponseEntity<List<Product>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String userId,
                                                 @RequestBody Product product) {
        product.setId(userId);
        Product updatedProduct = productService.updateProduct(product);
        ResponseEntity<Product> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        return productResponseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String userId) {
        productService.deleteProduct(userId);
        ResponseEntity<String> stringResponseEntity = null;
        stringResponseEntity = new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
        return stringResponseEntity;
    }
}

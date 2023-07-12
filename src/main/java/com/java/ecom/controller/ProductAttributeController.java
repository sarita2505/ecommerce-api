package com.java.ecom.controller;

import com.java.ecom.entity.ProductAttribute;
import com.java.ecom.service.productAttributeService.IProductAttributeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/products")
public class ProductAttributeController {
    @Autowired
    private IProductAttributeService productAttributeService;

    @PostMapping("/{productId}/attributes")
    public ResponseEntity<ProductAttribute> createAttributes(@PathVariable String productId, @RequestBody ProductAttribute productAttribute) {
        ProductAttribute savedUser = productAttributeService.createProdAttribute(productId, productAttribute);
        ResponseEntity<ProductAttribute> productAttributeResponseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        return productAttributeResponseEntity;
    }

    @GetMapping("/{productId}/attributes/{attributeId}")
    public ResponseEntity<ProductAttribute> getProdAttributeById(@PathVariable("attributeId") String attributeId) {
        ProductAttribute productAttribute = productAttributeService.getProdAttributeById(attributeId);
        if (null!=productAttribute.getId()) {
            return new ResponseEntity<>(productAttribute, HttpStatus.OK);
        }
        else {
            throw new RuntimeException();
        }
    }

    @GetMapping("/attributes")
    public ResponseEntity<List<ProductAttribute>> getProdAttribute() {
        List<ProductAttribute> productAttributes = productAttributeService.getAllAttributes();
        ResponseEntity<List<ProductAttribute>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(productAttributes, HttpStatus.OK);
        return listResponseEntity;
    }

    @GetMapping("/{productId}/attributes")
    public ResponseEntity<List<ProductAttribute>> getProdAttributeOfProduct(@PathVariable String productId) {
        List<ProductAttribute> productAttributes = productAttributeService.getAllAttributesOfProductByProdId(productId);
        ResponseEntity<List<ProductAttribute>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(productAttributes, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("/{productId}/attributes/{attributeId}")
    public ResponseEntity<ProductAttribute> updateProdAttribue(@RequestBody ProductAttribute productAttribute,@PathVariable("productId") String productId,@PathVariable("attributeId") String attributeId) {
        productAttribute.setId(attributeId);
        ProductAttribute attribute = productAttributeService.updateProdAttribute(productAttribute,productId);
        ResponseEntity<ProductAttribute> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(attribute, HttpStatus.OK);
        return productResponseEntity;
    }

    @DeleteMapping("/{productId}/attributes/{attributeId}")
    public ResponseEntity<String> deleteProdAttribue(@PathVariable("productId") String productId,@PathVariable("attributeId") String attributeId ) {
        productAttributeService.deleteProdAttribute(productId,attributeId);
        ResponseEntity<String> stringResponseEntity = null;
        stringResponseEntity = new ResponseEntity<>("product attribute successfully deleted!", HttpStatus.OK);
        return stringResponseEntity;
    }
}

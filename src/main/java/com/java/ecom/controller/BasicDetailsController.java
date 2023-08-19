package com.java.ecom.controller;

import com.java.ecom.entity.BasicDetailsEntity;
import com.java.ecom.service.BasicDetails.IBasicDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("basicDetails")
public class BasicDetailsController {
    private IBasicDetailsService basicDetailsService;

    @PostMapping
    public ResponseEntity<BasicDetailsEntity> createProduct(@RequestBody BasicDetailsEntity basicDetailsEntity) {
        BasicDetailsEntity savedUser = basicDetailsService.createBasicDetails(basicDetailsEntity);
        ResponseEntity<BasicDetailsEntity> detailsEntityResponseEntity = null;
        detailsEntityResponseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        return detailsEntityResponseEntity;
    }

    @GetMapping
    public ResponseEntity<List<BasicDetailsEntity>> getAllProducts() {
        List<BasicDetailsEntity> basicDetailsEntities = basicDetailsService.getBasicDetaila();
        ResponseEntity<List<BasicDetailsEntity>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(basicDetailsEntities, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<BasicDetailsEntity> updateStatusDetails(@PathVariable("id") String detailsId,
                                                  @RequestBody BasicDetailsEntity basicDetailsEntity) {
//        basicDetailsEntity.setId(detailsId);
        BasicDetailsEntity updateStatus = basicDetailsService.updateStatus(basicDetailsEntity,detailsId);
        ResponseEntity<BasicDetailsEntity> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(updateStatus, HttpStatus.OK);
        return productResponseEntity;
    }

}

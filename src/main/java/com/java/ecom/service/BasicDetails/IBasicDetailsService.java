package com.java.ecom.service.BasicDetails;

import com.java.ecom.entity.BasicDetailsEntity;

import java.util.List;

public interface IBasicDetailsService {
    BasicDetailsEntity createBasicDetails(BasicDetailsEntity basicDetailsEntity);

    BasicDetailsEntity getDetailsById(String detailsId);

    List<BasicDetailsEntity> getBasicDetaila();

    BasicDetailsEntity updateStatus(BasicDetailsEntity basicDetailsEntity,String productId);

//    void deleteImage(String productId,String imageId);
}

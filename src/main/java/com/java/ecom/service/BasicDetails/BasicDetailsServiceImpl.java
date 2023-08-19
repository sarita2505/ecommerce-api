package com.java.ecom.service.BasicDetails;

import com.java.ecom.entity.BasicDetailsEntity;
import com.java.ecom.repository.BasicDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasicDetailsServiceImpl implements IBasicDetailsService {
    @Autowired
    private BasicDetailsRepository basicDetailsRepository;

    @Override
    public BasicDetailsEntity createBasicDetails(BasicDetailsEntity basicDetailsEntity) {
        BasicDetailsEntity save = null;
        try {
            save = basicDetailsRepository.save(basicDetailsEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }


    @Override
    public BasicDetailsEntity getDetailsById(String detailsId) {
        Optional<BasicDetailsEntity> optionalUser = basicDetailsRepository.findById(detailsId);
        try {
            if (optionalUser.isPresent()) {
                return optionalUser.get();
            } else {
                throw new RuntimeException("resource for the id is not present");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<BasicDetailsEntity> getBasicDetaila() {
        List<BasicDetailsEntity> all = null;
        try {
            all = basicDetailsRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return all;
    }

    @Override
    public BasicDetailsEntity updateStatus(BasicDetailsEntity basicDetailsEntity, String detailsId) {
        BasicDetailsEntity existingBasicDetailsEntity = getDetailsById(detailsId);
        existingBasicDetailsEntity.setStatus(basicDetailsEntity.getStatus());
        BasicDetailsEntity updateBasicDetails = null;
        try {
            updateBasicDetails = basicDetailsRepository.save(existingBasicDetailsEntity);
        } catch (RuntimeException e) {
            throw e;
        }
        return updateBasicDetails;
    }
}


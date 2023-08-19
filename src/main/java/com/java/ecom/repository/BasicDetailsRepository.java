package com.java.ecom.repository;

import com.java.ecom.entity.BasicDetailsEntity;
import com.java.ecom.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicDetailsRepository extends JpaRepository<BasicDetailsEntity,String> {

}

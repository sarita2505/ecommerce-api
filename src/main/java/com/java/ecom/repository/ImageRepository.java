package com.java.ecom.repository;

import com.java.ecom.entity.Image;
import com.java.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,String> {

}

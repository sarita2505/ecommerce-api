package com.java.ecom.repository;

import com.java.ecom.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributrRepository extends JpaRepository<ProductAttribute,String> {
//    @Query("select a.color,a.size,a.stock,a.price,p.product from ProductAttribute a join a.product p where a.product= :productId")
    List<ProductAttribute> findByProductId(@Param("product_id")String productId);
}

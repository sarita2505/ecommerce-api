package com.java.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.java.ecom.annotation.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")

    private String id;
    //@Column(nullable = false)
    private String name;
    //@Column(nullable = false)
    private double price;
    //@Column(nullable = false)
    //private String[] images;
    private String description;
    @ProductType
    private String productType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//    @JsonIgnoreProperties("product")
    private List<Image> image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductAttribute> attributes;
}


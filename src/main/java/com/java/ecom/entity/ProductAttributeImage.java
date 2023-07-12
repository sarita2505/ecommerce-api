package com.java.ecom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TBL_PRODUCT_ATTRIBUTE_IMAGE")
public class ProductAttributeImage {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String imageLink;
    private int sequence;

    @ManyToOne
    @JoinColumn(name="attribute_id")
    @JsonBackReference
    private ProductAttribute attribute;
}

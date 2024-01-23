package com.MicroservicesProject.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private int price;

//    public Product(String id, String name, String description, BigDecimal price) {
//        this.id=id;
//        this.name=name;
//        this.description=description;
//        this.price=price;
//    }
}

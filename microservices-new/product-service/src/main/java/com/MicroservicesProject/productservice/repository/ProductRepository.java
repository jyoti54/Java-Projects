package com.MicroservicesProject.productservice.repository;

import com.MicroservicesProject.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

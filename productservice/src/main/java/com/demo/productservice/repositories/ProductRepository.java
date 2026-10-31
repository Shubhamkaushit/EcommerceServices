package com.demo.productservice.repositories;

import com.demo.productservice.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends MongoRepository<Product, String> {
}

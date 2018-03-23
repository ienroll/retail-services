package com.target.retail.rs.dao;

import com.target.retail.rs.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDao extends MongoRepository<ProductEntity, Long> {
    ProductEntity findById(Long id);
}


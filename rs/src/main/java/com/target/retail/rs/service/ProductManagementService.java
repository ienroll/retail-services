package com.target.retail.rs.service;

import com.target.retail.rs.model.Product;
import com.target.retail.rs.model.RepoProduct;
import org.springframework.stereotype.Service;

public interface ProductManagementService {
    Product getProduct(Long id);
    Product update(RepoProduct product);
}

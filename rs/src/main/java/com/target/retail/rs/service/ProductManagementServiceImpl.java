package com.target.retail.rs.service;

import com.target.retail.rs.dao.ProductDao;
import com.target.retail.rs.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    private Logger logger = LoggerFactory.getLogger(ProductManagementService.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RedSkyService redSkyService;


    public Product getProduct(Long id) {
        ProductEntity product1 = productDao.findById(id);
        Product product = new Product();
        if(product1==null){
            product.setResponse(buildResponse(400,"ERROR","Invalid ID supplied"));

        }else {
            product.setId(product1.getId());
            Price price = new Price();
            price.setCurrencyCode(product1.getCurrency());
            price.setValue(product1.getPrice());
            product.setCurrentPrice(price);
            String productName = redSkyService.getProductName(id);
            if(productName ==null){
                product.setResponse(buildResponse(200,"WARN","Unable to get product name from red sky"));
            }else{
                product.setResponse(buildResponse(200,"INFO",""));
                product.setName(productName);
            }
        }
        return product;
    }

    private Response buildResponse(int code, String type, String message) {
        Response response = new Response();
        response.code(code);
        response.type(type);
        response.message(message);
        return response;
    }

    private boolean insert(RepoProduct product) {
        try {
            ProductEntity product1 = new ProductEntity();
            product1.setId(product.getId());
            product1.setCurrency(product.getCurrentPrice().getCurrencyCode());
            product1.setPrice(product.getCurrentPrice().getValue());
            productDao.insert(product1);
            return true;
        }catch(Exception e){
            logger.error(" Error inserting product into Mongo Db",e);
            return false;
        }
    }

    public Product update(RepoProduct product) {
        Product p = new Product();
        p.setId(product.getId());
        Price price = new Price();
        price.setCurrencyCode(product.getCurrentPrice().getCurrencyCode());
        price.setValue(product.getCurrentPrice().getValue());
        p.setCurrentPrice(price);

        try {
            ProductEntity product1 = productDao.findById(product.getId());
            if (product1 == null) {
                if (insert(product))
                    p.setResponse(buildResponse(200, "INFO", "New Product Inserted"));
                else
                    p.setResponse(buildResponse(500, "ERROR", "Product Insertion failed due to internal error"));
            } else {
                product1.setCurrency(price.getCurrencyCode());
                product1.setPrice(price.getValue());
                productDao.save(product1);
                p.setResponse(buildResponse(200, "INFO", "Product Price Updated"));
            }
            String productName = redSkyService.getProductName(product.getId());
            if(productName == null){
                p.setResponse(buildResponse(200, "WARN", "Unable to get product name from red sky"));
            }
            p.setName(productName);
        }catch(Exception e){
            logger.error("Error updating Product with id = {}",product.getId(),e);
            p.setResponse(buildResponse(500, "ERROR", "Product Not Updated due to internal error"));
        }
        return p;
    }
}

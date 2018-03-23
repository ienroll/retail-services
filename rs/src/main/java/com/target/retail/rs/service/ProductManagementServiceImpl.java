package com.target.retail.rs.service;

import com.target.retail.rs.dao.ProductDao;
import com.target.retail.rs.model.ErrorResponse;
import com.target.retail.rs.model.Price;
import com.target.retail.rs.model.Product;
import com.target.retail.rs.model.ProductEntity;
import com.target.retail.rs.model.RepoProduct;
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
            product.setErrorResponse(buildErrorResponse(400,"ERROR","Invalid ID supplied"));

        }else {
            product.setId(product1.getId());
            Price price = new Price();
            price.setCurrencyCode(product1.getCurrency());
            price.setValue(product1.getPrice());
            product.setCurrentPrice(price);
            String productName = redSkyService.getProductName(id);
            if(productName ==null){
                product.setErrorResponse(buildErrorResponse(400,"ERROR","Product Not Found at red sky"));
            }else{
                product.setName(productName);
            }
        }
        return product;
    }

    private ErrorResponse buildErrorResponse(int code, String type, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(code);
        errorResponse.type(type);
        errorResponse.message(message);
        return errorResponse;
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
                    p.setErrorResponse(buildErrorResponse(200, "INFO", "New Product Inserted"));
                else
                    p.setErrorResponse(buildErrorResponse(400, "ERROR", "Product Insertion failed"));
            } else {
                product1.setCurrency(price.getCurrencyCode());
                product1.setPrice(price.getValue());
                productDao.save(product1);
                p.setErrorResponse(buildErrorResponse(200, "INFO", "Product Updated"));
            }
            p.setName(redSkyService.getProductName(product.getId()));
        }catch(Exception e){
            logger.error("Error updating Product with id = {}",product.getId(),e);
            p.setErrorResponse(buildErrorResponse(500, "INFO", "Product Not Updated due to internal error"));
        }
        return p;
    }
}

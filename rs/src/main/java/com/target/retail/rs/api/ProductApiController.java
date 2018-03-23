package com.target.retail.rs.api;


import com.target.retail.rs.model.RepoProduct;
import com.target.retail.rs.service.ProductManagementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.target.retail.rs.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductApiController implements ProductApi {
    private static Logger logger = LoggerFactory.getLogger(ProductApiController.class);

    @Autowired
    private ProductManagementService productManagementService;

    @ApiOperation(value = "Find product by ID", notes = "Returns a single product", response = Product.class, tags={ "product", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Product.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found", response = Product.class) })
    @RequestMapping(value = "/product/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public ResponseEntity<Product> getProductById(@ApiParam(value = "ID of product to return",required=true ) @PathVariable("id") Long id) {

        //validate(id);
        Product product = productManagementService.getProduct(id);
        if(product.getName() == null){
            return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
        } else if(product.getErrorResponse() == null) {
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } else{
            return new ResponseEntity<Product>(product,HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Updates a product in the store with price data", notes = "", response = Product.class, tags={ "product", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Product.class),
            @ApiResponse(code = 400, message = "Invalid IDsupplied", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found", response = Product.class),
            @ApiResponse(code = 405, message = "Invalid input", response = Product.class) })
    @RequestMapping(value = "/product/{id}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public ResponseEntity<Product> updateProductWithPrice(
            @ApiParam(value = "ID of product that needs to be updated",required=true ) @PathVariable("id") Long id,
            @ApiParam(value = "patient to return information for" ,required=true ) @RequestBody RepoProduct product) {

        if(product.getId() != id)
            return new ResponseEntity<Product>(new Product(),HttpStatus.BAD_REQUEST);

        Product product1 = productManagementService.update(product);
        if(product1.getName() == null){
            return new ResponseEntity<Product>(product1,HttpStatus.NOT_FOUND);
        } else if(product1.getErrorResponse().getCode() == 200) {
            return new ResponseEntity<Product>(product1, HttpStatus.OK);
        } else{
            return new ResponseEntity<Product>(product1,HttpStatus.BAD_REQUEST);
        }
    }

}

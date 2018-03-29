package com.target.retail.rs.api;

import com.target.retail.rs.model.Product;
import com.target.retail.rs.model.RepoProduct;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api(value = "product", description = "the product API")
public interface ProductApi {

    @ApiOperation(value = "Find product by ID", notes = "Returns a single product", response = Product.class, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Product.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Product.class) })
    @RequestMapping(value = "/product/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Product> getProductById(
@ApiParam(value = "ID of product to return",required=true ) @PathVariable("id") Long id


) {
        // do some magic!
        return new ResponseEntity<Product>(HttpStatus.OK);
    }


    @ApiOperation(value = "Updates a product in the store with price data", notes = "", response = Product.class, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Product.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Product.class) })
    @RequestMapping(value = "/product/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Product> updateProductWithPrice(
@ApiParam(value = "ID of product that needs to be updated",required=true ) @PathVariable("id") Long id


,

@ApiParam(value = "product to return information for" ,required=true ) @RequestBody RepoProduct product

) {
        // do some magic!
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

}

package com.target.retail.rs.service

import com.target.retail.rs.dao.ProductDao
import com.target.retail.rs.model.Price
import com.target.retail.rs.model.Product
import com.target.retail.rs.model.ProductEntity
import com.target.retail.rs.model.RepoProduct
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

import static org.junit.Assert.*
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class ProductManagementServiceSpec extends Specification{
    @Subject ProductManagementServiceImpl productManagementService;

    ProductDao productDao=Mock()
    RedSkyService redSkyService=Mock()

    def setup() {
        productManagementService = new ProductManagementServiceImpl()
        productManagementService.productDao = productDao
        productManagementService.redSkyService = redSkyService
    }
    def "test get Product - Positive"() {
        given: "given product id"
        Long id = 13860428

        when: "we get product "
        String productName = "testName"
        ProductEntity pe = new ProductEntity()
        pe.setId(id)
        redSkyService.getProductName(_)  >> {productName}
        productDao.findById(_) >>{pe}
        Product product  = productManagementService.getProduct(id)

        then: "we should get display product"
        assertNotNull(product)
        assertEquals(id,product.getId())
        assertEquals(productName,product.getName())

    }
    def "test get Product - Invalid id"() {
        given: "given product id"
        Long id = 1

        when: "we get product "
        redSkyService.getProductName(_)  >> {null}
        productDao.findById(_) >>{null}
        Product product  = productManagementService.getProduct(id)

        then: "we should get display product"
        assertNotNull(product)
        assertEquals(400,product.getResponse().getCode())
        assertEquals("Invalid ID supplied",product.getResponse().getMessage())

    }

    def "test get Product - No Product at redsky"() {
        given: "given product id"
        Long id = 13860428

        when: "we get product "
        ProductEntity pe = new ProductEntity()
        pe.setId(id)
        redSkyService.getProductName(_)  >> {null}
        productDao.findById(_) >>{pe}
        Product product  = productManagementService.getProduct(id)

        then: "we should not get product name"
        assertNotNull(product)
        assertEquals(id,product.getId())
        assertEquals(200,product.getResponse().getCode())
        assertEquals("Unable to get product name from red sky",product.getResponse().getMessage())

    }

    def "test update Product - No Product at redsky"() {
        given: "given repo product id"
        Long id = 13860428
        RepoProduct repoProduct = new RepoProduct()
        repoProduct.id(id)
        Price price = new Price()
        price.currencyCode("USD")
        price.value(100.00)
        repoProduct.currentPrice(price)

        when: "we update product "
        ProductEntity pe = new ProductEntity()
        pe.setId(id)
        redSkyService.getProductName(_)  >> {null}
        productDao.findById(_) >>{pe}
        productDao.save(_) >>{pe}
        Product product  = productManagementService.update(repoProduct)
        Product product1  = productManagementService.getProduct(id)
        then: "we should get updated product"
        assertNotNull(product)
        assertEquals(id,product.getId())
        assertNull(product.getName())
        assertEquals(200,product.getResponse().getCode())

        assertNotNull(product1)
        assertEquals(id,product1.getId())
        assertNull(product.getName())
        assertEquals(repoProduct.currentPrice.value(),product1.getCurrentPrice().value())
    }
}

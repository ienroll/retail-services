package com.target.retail.rs.service

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

import java.nio.file.Files
import java.nio.file.Paths

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertNull
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class RedSkyServiceSpec  extends Specification {
    @Subject RedSkyServiceImpl redSkyService;

    RestTemplate restTemplate=Mock()
    def setup() {
        redSkyService = new RedSkyServiceImpl()
        redSkyService.restTemplate = restTemplate
    }
    def "test get ProductName - Positive"() {
        given: "given product id"
        Long id = 13860428//16696652

        when: "we get product json"
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("JsonOutput.json").getFile())
        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()))
        redSkyService.restTemplate._(*_)  >> {lines.get(0)}
        String productName  = redSkyService.getProductName(id)

        then: "we should get product name"
        assertNotNull(productName)

    }
    def "test get ProductName - Negative"() {
        given: "given product id"
        Long id = 16696652

        when: "we get connection error"
        redSkyService.restTemplate._(*_)  >> { throw new RuntimeException() }
        String productName  = redSkyService.getProductName(id)

        then: "we should get nullresponse"
        assertNull(productName)

    }
}

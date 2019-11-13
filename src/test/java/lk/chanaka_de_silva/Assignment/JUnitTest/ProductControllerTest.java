/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.chanaka_de_silva.Assignment.JUnitTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lk.chanaka_de_silva.Assignment.AssignmentApplication;
import lk.chanaka_de_silva.Assignment.constant.TestConstant;
import lk.chanaka_de_silva.Assignment.module.Category;
import lk.chanaka_de_silva.Assignment.module.Product;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author chanaka
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssignmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    void contextLoads() {
        System.out.println("++++++++++ TEST PRODUCT ++++++++++");
        restTemplate.getRestTemplate().getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @PostConstruct
    public void initialize() {
        restTemplateBuilder.rootUri(TestConstant.getRootUrl()).build();
        restTemplate = new TestRestTemplate(restTemplateBuilder, TestConstant.USER_NAME, TestConstant.PASSWORD, TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
    }

    @Test
    public void testFindAllProducts() {
        Product[] response = restTemplate.getForObject(TestConstant.PRODUCT_END_POINT + "/find-all", Product[].class);
        assertNotNull(response);
    }

    @Test
    public void testFindProductByProductId() {
        int productId = 11;
        Product getProducts = restTemplate.getRestTemplate().getForObject(TestConstant.PRODUCT_END_POINT + "/find-one/" + productId, Product.class);
        System.out.println(getProducts.toString());
        assertNotNull(getProducts);
    }

    @Test
    public void testSaveProductWithNewCategorys() {
        //new product Ob
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("Description");
        product.setPrice(new BigDecimal("100"));

        //new Category Ob 1
        Category category1 = new Category();
        category1.setName("Category 1");
        category1.setDescription("Description");

        //new Category Ob 2
        Category category2 = new Category();
        category2.setName("Category 2");
        category2.setDescription("Description");

        List<Category> categorys = new ArrayList<>();
        categorys.add(category1);
        categorys.add(category2);

        product.setCategories(categorys);

        ResponseEntity<Product> postResponse = restTemplate.postForEntity(TestConstant.PRODUCT_END_POINT + "/save", product, Product.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testSaveProductWithExistingCategorys() {

        int categoryId1 = 21;
        int categoryId2 = 22;

        //new product details Ob
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("Description");
        product.setPrice(new BigDecimal("100"));

        //find by category by id
        Category findCategory1 = restTemplate.getRestTemplate().getForObject(TestConstant.CATEGORY_END_POINT + "/find-one/" + categoryId1, Category.class);
        Category findCategory2 = restTemplate.getRestTemplate().getForObject(TestConstant.CATEGORY_END_POINT + "/find-one/" + categoryId2, Category.class);

        List<Category> categorys = new ArrayList<>();
        categorys.add(findCategory1);
        categorys.add(findCategory2);

        //set categry list new product Ob
        product.setCategories(categorys);

        ResponseEntity<Product> postResponse = restTemplate.postForEntity(TestConstant.PRODUCT_END_POINT + "/save", product, Product.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateProduct() {
        int productId = 11;
        //get product details by id
        Product findProduct = restTemplate.getForObject(TestConstant.PRODUCT_END_POINT + "/find-one/" + productId, Product.class);
        System.out.println("Exist Product - " + findProduct.toString());

        //change product details
        findProduct.setName("Update Name");
        findProduct.setDescription("Update Description");
        findProduct.setPrice(BigDecimal.ONE);

        //save or update product details
        //save = id is null
        //update = id is not null
        ResponseEntity<Product> postResponse = restTemplate.postForEntity(TestConstant.PRODUCT_END_POINT + "/save", findProduct, Product.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testDeleteProduct() {
        int productId = 11;
        Product getProduct = restTemplate.getForObject(TestConstant.PRODUCT_END_POINT + "/find-one/" + productId, Product.class);
        assertNotNull(getProduct);

        try {
           restTemplate.delete(TestConstant.PRODUCT_END_POINT + "/delete/" + getProduct.getId());
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.chanaka_de_silva.Assignment.JUnitTest;

import javax.annotation.PostConstruct;
import lk.chanaka_de_silva.Assignment.AssignmentApplication;
import lk.chanaka_de_silva.Assignment.constant.TestConstant;
import lk.chanaka_de_silva.Assignment.module.Category;
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
public class CategoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    void contextLoads() {
        System.out.println("++++++++++ TEST CATEGORY ++++++++++");
        restTemplate.getRestTemplate().getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @PostConstruct
    public void initialize() {
        restTemplateBuilder.rootUri(TestConstant.getRootUrl()).build();
        restTemplate = new TestRestTemplate(restTemplateBuilder, TestConstant.USER_NAME, TestConstant.PASSWORD, TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
    }

    @Test
    public void testFindAllProducts() {
        Category[] response = restTemplate.getForObject(TestConstant.CATEGORY_END_POINT + "/find-all", Category[].class);
        assertNotNull(response);
    }

    @Test
    public void testFindCategoryByCategoryId() {
        int categoryId = 20;
        Category getCategory = restTemplate.getRestTemplate().getForObject(TestConstant.CATEGORY_END_POINT + "/find-one/" + categoryId, Category.class);
        System.out.println(getCategory.toString());
        assertNotNull(getCategory);
    }

    @Test
    public void testSaveCategory() {
        //new Category Ob 1
        Category category = new Category();
        category.setName("Category 1");
        category.setDescription("Description");

        ResponseEntity<Category> postResponse = restTemplate.postForEntity(TestConstant.CATEGORY_END_POINT + "/save", category, Category.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateCategory() {
        int categoryId = 20;

        //find by category by id
        Category findCategory = restTemplate.getRestTemplate().getForObject(TestConstant.CATEGORY_END_POINT + "/find-one/" + categoryId, Category.class);

        //change category details
        findCategory.setName("Update Name");
        findCategory.setDescription("Update Description");

        //save or update category details
        //save = id is null
        //update = id is not null
        ResponseEntity<Category> postResponse = restTemplate.postForEntity(TestConstant.CATEGORY_END_POINT + "/save", findCategory, Category.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testDeleteCategory() {
        int categoryId = 20;
        Category getCategory = restTemplate.getForObject(TestConstant.CATEGORY_END_POINT + "/find-one/" + categoryId, Category.class);
        assertNotNull(getCategory);

        try {
            restTemplate.delete(TestConstant.CATEGORY_END_POINT + "/delete/" + getCategory.getId());
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}

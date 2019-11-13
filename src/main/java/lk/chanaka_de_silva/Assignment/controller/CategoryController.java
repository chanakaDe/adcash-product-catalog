/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.chanaka_de_silva.Assignment.controller;

import java.util.List;
import lk.chanaka_de_silva.Assignment.module.Category;
import lk.chanaka_de_silva.Assignment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chanaka
 */
@RestController
@CrossOrigin
@RequestMapping("/api/assignment/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/find-all", produces = {
                MediaType.APPLICATION_JSON_VALUE, //-> JSON encoding
                MediaType.APPLICATION_XML_VALUE // -> XML encoding
            }
    )
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/find-one/{categoryId}", produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Category findOne(@PathVariable Integer categoryId) {
        return categoryService.findOne(categoryId);
    }

    @PostMapping(value = "/save") 
//            produces = {
//        MediaType.APPLICATION_JSON_VALUE, 
//        MediaType.APPLICATION_XML_VALUE})
    public Category save(@RequestBody Category categories) {
        return categoryService.save(categories);
    }

    @DeleteMapping(value = "/delete/{categoryId}", produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Integer delete(@PathVariable Integer categoryId) {
        return categoryService.delete(categoryId);
    }

}

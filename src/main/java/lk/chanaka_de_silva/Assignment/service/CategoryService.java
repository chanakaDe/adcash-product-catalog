/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.chanaka_de_silva.Assignment.service;

import java.util.List;
import lk.chanaka_de_silva.Assignment.exception.handler.EntityNotFoundException;
import lk.chanaka_de_silva.Assignment.module.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lk.chanaka_de_silva.Assignment.repository.CategoryRepository;

/**
 *
 * @author chanaka
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category categories) {
        return categoryRepository.save(categories);
    }

    public Category findOne(Integer categoryId) {
        Category getCategory = categoryRepository.getOne(categoryId);
        if (null != getCategory) {
            return getCategory;
        } else {
            throw new EntityNotFoundException("CATEGORY NOT FOUND");
        }
    }

    public Integer delete(Integer categoryId) {
        Category getCategory = categoryRepository.getOne(categoryId);
        if (null != getCategory) {
            categoryRepository.delete(getCategory);
            return categoryId;
        } else {
            throw new EntityNotFoundException("CATEGORY NOT FOUND");
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.chanaka_de_silva.Assignment.service;

import java.util.List;
import lk.chanaka_de_silva.Assignment.exception.handler.EntityNotFoundException;
import lk.chanaka_de_silva.Assignment.module.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lk.chanaka_de_silva.Assignment.repository.ProductRepository;

/**
 *
 * @author chanaka
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product save(Product products) {
        return productRepository.save(products);
    }

    public Product findOne(Integer productId) {
        Product getProduct = productRepository.getOne(productId);
        if (null != getProduct) {
            return getProduct;
        } else {
            throw new EntityNotFoundException("PRDUCT NOT FOUND");
        }
    }

    public Integer delete(Integer productId) {
        Product getProduct = productRepository.getOne(productId);
        if (null != getProduct) {
            productRepository.delete(getProduct);
            return productId;
        } else {
            throw new EntityNotFoundException("PRDUCT NOT FOUND");
        }
    }

}

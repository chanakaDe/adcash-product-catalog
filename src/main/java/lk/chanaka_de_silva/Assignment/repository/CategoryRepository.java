/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.chanaka_de_silva.Assignment.repository;

import lk.chanaka_de_silva.Assignment.module.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chanaka
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}

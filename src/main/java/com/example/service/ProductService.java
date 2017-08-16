/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.Customer;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.CustomerRepository;

/**
 *
 * @author User
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts(long id){
        
        List<Product> p = new ArrayList<>();
        productRepository.findAll()
        .forEach(p::add);
        
        return p;
    }
    
    public Product getProduct(long id){
        return productRepository.findOne(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(long id, Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.delete(id);
    }
}

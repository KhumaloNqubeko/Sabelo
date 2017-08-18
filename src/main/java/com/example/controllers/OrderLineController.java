/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Customer;
import com.example.entity.Holder;
import com.example.entity.OrderLine;
import com.example.entity.Product;
import com.example.repository.OrderLineRepo;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
public class OrderLineController {
    
    @Autowired
    private ProductRepository cartRepository;
    @Autowired
    private OrderLineRepo orderLineRepo;
    
    /*
    @RequestMapping
    public void placeOrder(@RequestBody Holder holder){
        
        
        
        OrderLine ol = new OrderLine();
        
        for(Product cart : cartRepository.findAll()){
            
            
        }
        
    }*/
}

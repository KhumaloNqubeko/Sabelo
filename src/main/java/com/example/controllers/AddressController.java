/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Customer;
import com.example.entity.DeliveryAddress;
import com.example.entity.Product;
import com.example.repository.AddressRepo;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
public class AddressController {
    
    @Autowired
    private AddressRepo repo;
    
    @Autowired
    private ProductRepository prodRepo;
    
    @RequestMapping(value = "/address/add", method = RequestMethod.POST)
    public void addAddress(@RequestBody DeliveryAddress ad){
        
        repo.save(ad);
        
        for(Product p : prodRepo.findAll()){
            prodRepo.delete(p);
        }
    }
    
    @RequestMapping(value = "/getAddress", method = RequestMethod.POST)
    public DeliveryAddress getAddress(@RequestBody Customer customer){
        DeliveryAddress d = new DeliveryAddress();
        
        for(DeliveryAddress da : repo.findAll()){
            if(da.getCustID() == customer.getId()){
                d = da;
            }
        }
        
        return d;
    }
}

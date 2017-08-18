/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Login;
import com.example.entity.Admin;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import java.util.Arrays;
import java.util.List;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.CustomerRepository;
import com.example.repository.LoginRepository;

import java.util.ArrayList;
import org.springframework.http.MediaType;

/**
 *
 * @author User
 */
@RestController
public class CustomerController {
    
  
    
    @Autowired
    private CustomerRepository repository;
    
    @Autowired
    private LoginRepository loginRepo;
    
    @RequestMapping("/customers")
    public List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        
        for(Customer c : repository.findAll()){
            customers.add(c);
        }
        
        return customers;
    }
    
    @RequestMapping("/customer/{id}")
    public Customer getPerson(@PathVariable long id){
        return repository.findOne(id);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/customers/create")
    public void addPerson(@RequestBody Customer c){
        repository.save(c);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/customers/login")
    public Customer loginCust(@RequestBody Login login){
        Customer customer = new Customer();
        
        for(Customer c : repository.findAll()){
           
            String u = login.getUsername();
            String p = login.getPassword();
            
            if(u.equals(c.getUsername()) && p.equals(c.getPassword())){
                loginRepo.save(login);
                customer = c;
            }
        }
        return customer;
        
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/customer/{id}")
    public void updatePerson(@RequestBody Customer c, @PathVariable long id){
        repository.save(c);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
    public void deletePerson(@RequestBody long id){
        repository.delete(id);
    }
}

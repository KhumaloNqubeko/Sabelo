/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Login;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.repository.LoginRepository;
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
public class LoginController {
    
    @Autowired
    private CustomerRepository customerRepo;
    
    @Autowired
    private LoginRepository loginRepo;
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/customer/signin")
    public Customer loginCustomer(@RequestBody Login login){
        Customer customer = new Customer();
        
        for(Customer c : customerRepo.findAll()){
            if(c.getUsername().equalsIgnoreCase(login.getUsername()) && c.getPassword().equals(login.getPassword())){
                customer = c;
            }
        }
        return customer;
        
    }
    
    @RequestMapping(value = "/login/getUser")
    public Customer getUser(){
        Customer c = new Customer();
        Login l = new Login();
        
        for(Login login : loginRepo.findAll()){
            l = login;
        }
        
        for(Customer cust : customerRepo.findAll()){
            if(l.getUsername().equals(cust.getUsername()) && l.getPassword().equals(cust.getPassword())){
                c = cust;
            }
        }
        
        return c;
    }
}

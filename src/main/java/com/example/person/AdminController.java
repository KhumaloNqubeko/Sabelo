/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.person;

import com.example.entity.Admin;
import com.example.entity.Customer;
import com.example.repository.AdminRepo;
import com.example.utility.Login;
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
public class AdminController {
    @Autowired
    private AdminRepo repo;
    
    
    
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public Admin adminLogin(@RequestBody Admin a){
        Admin admin = new Admin();
        
        for(Admin ad : repo.findAll()){
            if(ad.getUsername().equals(a.getUsername()) && ad.getPassword().equals(a.getPassword())){
                admin = ad;
            }
        }
        
        return admin;
    }
    
}

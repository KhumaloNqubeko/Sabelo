/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.entity.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author User
 */
public interface AdminRepo extends CrudRepository<Admin, Long>{
    
}

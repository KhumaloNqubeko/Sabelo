/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Stock;
import com.example.repository.StockRepo;
import java.util.ArrayList;
import java.util.List;
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
public class StockController {
    
    @Autowired
    private StockRepo repo;
    
    @RequestMapping(value = "/admin/stock")
    public List<Stock> getAllStock(){
        List<Stock> stock = new ArrayList<>();
        
        for(Stock s : repo.findAll()){
            stock.add(s);
        }
        
        return stock;
    }
    
    @RequestMapping(value = "/admin/stock", method = RequestMethod.POST)
    public void addStock(@RequestBody Stock stock){
        repo.save(stock);
    }
    
    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public void deleteStock(@RequestBody Stock stock){
        repo.delete(stock);
    }  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Customer;
import com.example.entity.OrderLine;
import com.example.entity.Product;
import com.example.entity.Stock;
import com.example.repository.OrderLineRepo;
import com.example.repository.ProductRepository;
import com.example.service.CustomerService;
import com.example.service.ProductService;
import com.example.utility.Utility;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
public class ProductController {


    @Autowired
    private ProductRepository repository;
    
    @Autowired
    private OrderLineRepo orderRepo;

    private int quantity;

    @RequestMapping("/shop/products")
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        repository.findAll().forEach(products::add);

        return products;
    }

    @RequestMapping("/shop/{id}/product/{productId}")
    public Product getProduct(@PathVariable long id) {
        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shop/products")
    public void addProduct(@RequestBody Stock product) {
        boolean isAvailable = false;

        for (Product p : repository.findAll()) {
            if (p.getDescription().equals(product.getDescription())
                    && p.getPrice() == product.getPrice()) {
                isAvailable = true;
                p.setQuantity(p.getQuantity() + 1);
            }
        }

        Product prod = new Product();

        if (!isAvailable) {
            prod.setDescription(product.getDescription());
            prod.setPrice(product.getPrice());
            prod.setImageURL(product.getImage());
            prod.setQuantity(1);
            prod.setTotal(0.0);

        }
        repository.save(prod);
        for(Product p : repository.findAll()){
            
            if(p.getQuantity() == 0){
                repository.delete(p);
            }            
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shop/products/total")
    public String calculateTotal() {

        double t = 0;
        for (Product p : repository.findAll()) {
            t += p.getPrice();
        }
        DecimalFormat fm = new DecimalFormat("##.##");

        String total = fm.format(t);

        return total;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/product/update1")
    public int getQuantity(@RequestBody int qty) {

        quantity = qty;

        return quantity;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/product/update")
    public Product updateProduct(@RequestBody Product product) {
        return product;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shop/product/delete")
    public void deleteProduct(@RequestBody Product id) {
        repository.delete(id);
    }
    
}

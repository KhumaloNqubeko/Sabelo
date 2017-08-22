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
import com.example.repository.CustomerRepository;
import com.example.repository.OrderLineRepo;
import com.example.repository.ProductRepository;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderLineController {

    @Autowired
    private ProductRepository cartRepository;
    @Autowired
    private OrderLineRepo orderLineRepo;
    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(value = "/orders")
    public List<Customer> getOrders() {
        List<Customer> customers = new ArrayList<>();
        List<Customer> custs = (List<Customer>) customerRepo.findAll();

        for (OrderLine or : orderLineRepo.findAll()) {
            for (Customer c : custs) {
                if (or.getCustomer() == c.getId()) {
                    customers.add(c);
                }
            }
        }
        /*
        List<Customer> custDup = new ArrayList<>();
        
        for(int x = 0; x < customers.size(); x++){
            if(customers.get(x).getId() != customers.get(x + 1).getId()){
                custDup.add(customers.get(x));
            }
        }
         */
        return customers;
    }

    public String calculateTotal() {

        double t = 0;
        for (Product p : cartRepository.findAll()) {
            t += p.getPrice();
        }
        DecimalFormat fm = new DecimalFormat("##.##");

        String total = fm.format(t);

        return total;
    }

    @RequestMapping(value = "/order/place", method = RequestMethod.POST)
    public void placeOrder(@RequestBody Customer customer) {
        String t = calculateTotal();

        List<Product> ps = new ArrayList<>();

        cartRepository.findAll().forEach(ps::add);

        for (int x = 0; x < ps.size(); x++) {
            OrderLine or = new OrderLine(ps.get(x).getDescription(),
                    ps.get(x).getQuantity(), ps.get(x).getPrice(),
                    ps.get(x).getImageURL(), Double.parseDouble(t), customer.getId());
            orderLineRepo.save(or);
        }
    }

    @RequestMapping(value = "/getOrders", method = RequestMethod.POST)
    public List<OrderLine> getOrders(@RequestBody Customer customer) {
        List<OrderLine> orders = new ArrayList<>();
        
        System.out.println(customer.getName() + " " + customer.getAddress());
        
        for (OrderLine or : orderLineRepo.findAll()) {
            /*if (or.getCustomer() == customer.getId()) {
                orders.add(or);
            }*/
        }

        return orders;
    }
}

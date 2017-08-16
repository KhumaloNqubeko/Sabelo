/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.Customer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.CustomerRepository;

/**
 *
 * @author User
 */
@Service
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {

        List<Customer> p = new ArrayList<>();
        customerRepository.findAll()
                .forEach(p::add);

        return p;
    }

    public Customer getCustomer(long id) {
        return customerRepository.findOne(id);
    }

    public void addCustomer(Customer c) {
        customerRepository.save(c);
    }

    public void updateCustomer(long id, Customer c) {
        customerRepository.save(c);
    }

    public void deleteCustomer(long id) {
        customerRepository.delete(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Payment;
import com.example.repository.PaymentRepo;
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
public class PaymentController {
    @Autowired
    private PaymentRepo paymentRepo;
    
    @RequestMapping(value = "/payment/add", method = RequestMethod.POST)
    public void addPayment(@RequestBody Payment payment){
        paymentRepo.save(payment);
    }
}

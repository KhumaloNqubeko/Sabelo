/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

/**
 *
 * @author User
 */
public class Holder {
    
    private Customer customer;
    private OrderLine orderLine;
    private DeliveryAddress address;

    public Holder() {
    }

    public Holder(Customer customer, OrderLine orderLine, DeliveryAddress address) {
        this.customer = customer;
        this.orderLine = orderLine;
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    public DeliveryAddress getAddress() {
        return address;
    }

    public void setAddress(DeliveryAddress address) {
        this.address = address;
    }
       
}

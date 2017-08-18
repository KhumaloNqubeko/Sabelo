/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity(name = "deliveryaddress")
public class DeliveryAddress implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String city;
    private String suburb;
    private String street;
    private String snumber;
    private String bname;
    private String unumber;
    private long custID;

    public DeliveryAddress() {
    }

    public DeliveryAddress(long id, String city, String suburb, String street, String snumber, String bname, String unumber, long custID) {
        this.id = id;
        this.city = city;
        this.suburb = suburb;
        this.street = street;
        this.snumber = snumber;
        this.bname = bname;
        this.unumber = unumber;
        this.custID = custID;
    }

    public long getCustID() {
        return custID;
    }

    public void setCustID(long custID) {
        this.custID = custID;
    }

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getUnumber() {
        return unumber;
    }

    public void setUnumber(String unumber) {
        this.unumber = unumber;
    }
    
    
}

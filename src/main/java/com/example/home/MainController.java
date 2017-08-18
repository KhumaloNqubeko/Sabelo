/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author User
 */
@Controller
public class MainController {
    
    @GetMapping("/")
    private String indexHome(){
        return "index";
    }
    
    @GetMapping("/register")
    private String registerCustomer(){
        return "register";
    }
    
    @GetMapping("/login")
    private String loginCustomer(){
        return "login";
    }
    
    @GetMapping("/shop")
    private String myShop(){
        return "shop";
    }
    
    @GetMapping("/admin")
    private String admin(){
        return "admin";
    }
    
    
    @GetMapping("/stock")
    private String addStock(){
        return "addStock";
    }
    
    @GetMapping("/allProducts")
    private String getAllProducts(){
        return "allProducts";
    }
    
    @GetMapping("/viewMoreLogin")
    private String getViewMoreLogin(){
        return "viewMoreLogin";
    }
    
    @GetMapping("/Cart")
    private String getCart(){
        return "Cart";
    }
    
    @GetMapping("/checkout")
    private String getCheckout(){
        return "Checkout";
    }
    
    @GetMapping("/thank")
    private String getLastPage(){
        return "thank";
    }
    
    @GetMapping("/getStock")
    private String getStockPage(){
        return "myStock";
    }
    
    @GetMapping("/update")
    private String getUpdatePage(){
        return "update";
    }
    
    @GetMapping("/div")
    private String getPage(){
        return "bootIndex";
    }
    
    @GetMapping("/address")
    private String getAddressPage(){
        return "addressDetails";
    }
}

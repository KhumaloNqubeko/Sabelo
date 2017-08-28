/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Stock;
import com.example.entity.UploadFile;
import com.example.repository.FilesUpload;
import com.example.repository.StockRepo;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@RestController
public class StockController extends HttpServlet {

    HttpServletRequest request;

    @Autowired
    private StockRepo repo;

    @Autowired
    private FilesUpload filesRepo;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/admin/stock")
    public List<Stock> getAllStock() {
        List<Stock> stock = new ArrayList<>();

        for (Stock s : repo.findAll()) {
            stock.add(s);
        }

        return stock;
    }

    @RequestMapping(value = "/admin/stock", method = RequestMethod.POST)
    public @ResponseBody
    void addStock(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "description") String description, @RequestParam(value = "category") String category,
            @RequestParam(value = "price") double price) throws IOException {
        
        byte[] bytes = file.getBytes();
        
        Stock stock = new Stock();
        stock.setDescription(description);
        stock.setImage(bytes);
        stock.setCategory(category);
        stock.setPrice(price);
        repo.save(stock);
        
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public void deleteStock(@RequestBody Stock stock) {
        repo.delete(stock);
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public @ResponseBody
    void upload(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "stockNumber") int stockNumber) throws IOException {
        String fileName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();

        UploadFile up = new UploadFile();
        up.setData(bytes);
        up.setFileName(fileName);
        up.setId(665L);
        filesRepo.save(up);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("C:/Users/User/Desktop/temp/" + fileName)));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.close();
    }
}

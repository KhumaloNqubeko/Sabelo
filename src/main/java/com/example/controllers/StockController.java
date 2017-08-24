/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entity.Stock;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@RestController
public class StockController {

    @Autowired
    private StockRepo repo;

    @Autowired
    private FilesUpload filesRepo;

    @RequestMapping(value = "/admin/stock")
    public List<Stock> getAllStock() {
        List<Stock> stock = new ArrayList<>();

        for (Stock s : repo.findAll()) {
            stock.add(s);
        }

        return stock;
    }

    @RequestMapping(value = "/admin/stock", method = RequestMethod.POST)
    public void addStock(@RequestBody Stock stock) {

        repo.save(stock);
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public void deleteStock(@RequestBody Stock stock) {
        repo.delete(stock);
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {

                System.out.println("Saving file: " + aFile.getOriginalFilename());

                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());
                filesRepo.save(uploadFile);
            }
        }

        return "Success";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)

    public String upload(@RequestParam("name") String name,
            @RequestParam(value = "file", required = true) MultipartFile file) //@RequestParam ()CommonsMultipartFile[] fileUpload
    {
        // @RequestMapping(value="/newDocument", , method = RequestMethod.POST)
        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
               // fileSystemHandler.create(123, fileContent, name);
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

    @RequestMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("" + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
}

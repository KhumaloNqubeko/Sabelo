/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import com.example.controllers.*;
import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "FILES_UPLOAD")
public class UploadFile implements Serializable{
    private long id;
    private String fileName;
    private byte[] data;
    private long stockID;

    public long getStockID() {
        return stockID;
    }

    public void setStockID(long stockID) {
        this.stockID = stockID;
    }

 
    @Id
    @GeneratedValue
    @Column(name = "FILE_ID")
    public long getId() {
        return id;
    }

 
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "FILE_NAME")
    public String getFileName() {
        return fileName;
    }
 
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
 
    @Column(name = "FILE_DATA")
    public byte[] getData() {
        return data;
    }
 
    public void setData(byte[] data) {
        this.data = data;
    }
}
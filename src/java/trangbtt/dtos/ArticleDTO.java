/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.dtos;

import java.io.Serializable;

/**
 *
 * @author trang
 */
public class ArticleDTO implements Serializable{
    private int id;
    private String title, description, content, author, date, status ;

    public ArticleDTO() {
    }

    public ArticleDTO(String title) {
        this.title = title;
    }

    
    public ArticleDTO(int id,String title, String description, String author, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.date = date;
    }
    
    
    
    public ArticleDTO(String title, String description, String content, String author, String date) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public ArticleDTO(int id, String title, String description, String content, String author, String date, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.date = date;
        this.status = status;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

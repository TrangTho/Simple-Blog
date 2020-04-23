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
public class CommentDTO implements Serializable{
    private int idComment;
    private String user, content, date;
    private int idArticle;

    public CommentDTO() {
    }

    public CommentDTO(String user, String content) {
        this.user = user;
        this.content = content;
    }

    
    
    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
    
    
}

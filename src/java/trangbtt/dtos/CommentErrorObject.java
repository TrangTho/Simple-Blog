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
public class CommentErrorObject implements Serializable{
    private String contentError;

    public CommentErrorObject() {
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }
    
}

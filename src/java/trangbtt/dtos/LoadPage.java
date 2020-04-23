/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author trang
 */
public class LoadPage implements Serializable{
    private int totalPage;
    private List<ArticleDTO> listActicle;


    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ArticleDTO> getListActicle() {
        return listActicle;
    }

    public void setListActicle(List<ArticleDTO> listActicle) {
        this.listActicle = listActicle;
    }
}
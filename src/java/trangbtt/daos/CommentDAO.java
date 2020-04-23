/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import trangbtt.dbs.MyConnection;
import trangbtt.dtos.CommentDTO;

/**
 *
 * @author trang
 */
public class CommentDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;

    public CommentDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pre != null) {
            pre.close();
        }
        if (conn != null) {
            conn.close();
        }

    }

    public String roleByComment(String email) throws Exception {
        String role =null;
        try {
            String sql = "select  re.Role from Registration re where re.Email = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            rs = pre.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public boolean insertComment(String content, String user, String idBlog) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Comment(ContentComment, UserComment, ID) values(?,?,?)";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, content);
            pre.setString(2, user);
            pre.setString(3, idBlog);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean deleteCmt(String user) throws Exception{
        boolean check = false;
        try{
            String sql = "Delete From Comment Where UserComment like ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+user+ "%");
            check = pre.executeUpdate() >0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public List<CommentDTO> findByLikeIDInComment(String id) throws Exception {
        List<CommentDTO> result = null;
        CommentDTO dto = null;
        String user = null;
        String comment = null;

        try {
            String sql = "Select UserComment, ContentComment From Comment Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                user = rs.getString("UserComment");
                comment = rs.getString("ContentComment");
                dto = new CommentDTO(user, comment);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}

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
import java.sql.SQLException;
import javax.naming.NamingException;
import trangbtt.dbs.MyConnection;
import trangbtt.dbs.SendingEmail;

/**
 *
 * @author trang
 */
public class RegistrationDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;

    public RegistrationDAO() {
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

    public String checkLogin(String email, String password) throws Exception {
        String role = "failed";
        try {
            String sql = "Select Role From Registration Where Email = ? and Password = ? and Status = 'Active'";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public String loadNameMember(String email) throws Exception {
        String name = "";
        try {
            String sql = "Select Name From Registration Where Email = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            rs = pre.executeQuery();
            if (rs.next()) {
                name = rs.getString("Name");
            }
        } finally {
            closeConnection();
        }
        return name;
    }

    public boolean insertNewAccount(String email, String name, String password, String code) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Registration(Email, Name, Password, Role,Status, CheckMail) values(?,?,?,'member','New',?)";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, name);
            pre.setString(3, password);
            pre.setString(4, code);

            int result = pre.executeUpdate();
            if (result != 0) {
                SendingEmail se = new SendingEmail(email, code);
                se.senMail();
                check = true;
            }

        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean updateUser(String user) throws Exception{
        boolean check = false;
        try{
            String sql = "Update Registration set status = 'Delete' where email like ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,"%" +user+"%");
            check = pre.executeUpdate() > 0;
            
        }finally{
            closeConnection();
        }
        return check;
    }
    public boolean activeAccount(String email, String code) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Update Registration set status='Active' where email = ? and CheckMail = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, code);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}

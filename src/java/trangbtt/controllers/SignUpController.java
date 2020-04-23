/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trangbtt.daos.RegistrationDAO;
import trangbtt.dtos.RegistrationErrorObject;
import trangbtt.hashFunctions.SHA256;

/**
 *
 * @author trang
 */
public class SignUpController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "active.jsp";
    private static final String INVALID = "signUp.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try { 
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");

            RegistrationErrorObject err = new RegistrationErrorObject();
            boolean valid = true;

            if (!email.matches("^[A-Za-z0-9]+@(.+)$")) {
                err.setEmailError("Error fomat email (EX: abc@gmai.com)");
                valid = false;
            }
            if (email.length() == 0) {
                err.setEmailError("Email can't be blank");
                valid = false;
            }
            if (password.length() == 0) {
                err.setPasswordError("Password can't be blank");
                valid = false;
            }
            if (password.length() < 5) {
                err.setPasswordError("Password must be greater than 5 character");
                valid = false;
            }
            if (!confirm.equals(password)) {
                err.setConfirmError("Confirm must match Password");
                valid = false;
            }
            if (confirm.length() == 0) {
                err.setConfirmError("Confirm password can't be blank");
                valid = false;
            }
            if (name.length() == 0) {
                err.setNameError("Name can't be blank");
                valid = false;
            }
            if (valid) {
                RegistrationDAO dao = new RegistrationDAO();
                SHA256 sha = new SHA256();

                String pass = sha.toHexString(sha.getSHA(password));
                Random random = new Random();
                random.nextInt(999999);
                String code = sha.toHexString(sha.getSHA(random.toString()));
                boolean result = dao.insertNewAccount(email, name, pass, code);
                if (result) {
                    url = SUCCESS;
                    request.setAttribute("EMAIL", email);
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", err);
            }
        } catch (Exception e) {
            log("Error at SignUpController " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                RegistrationErrorObject errObj = new RegistrationErrorObject();
                errObj.setEmailError("Email is exited");
                url = INVALID;
                request.setAttribute("INVALID", errObj);
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

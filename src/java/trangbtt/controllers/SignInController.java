/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trangbtt.daos.RegistrationDAO;
import trangbtt.dtos.RegistrationErrorObject;
import trangbtt.hashFunctions.SHA256;

/**
 *
 * @author trang
 */
public class SignInController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "ListOfAdminController";
    private static final String USER = "index.jsp";
    private static final String INVALID = "signIn.jsp";

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
            String password = request.getParameter("txtPassword");

            RegistrationErrorObject error = new RegistrationErrorObject();
            boolean valid = true;
            if (!email.matches("^[A-Za-z0-9]+@(.+)$")) {
                error.setEmailError("Error fomat email (EX: abc@gmai.com)");
                valid = false;
            }
            if (email.length() == 0) {
                error.setEmailError("Email can't be blank");
                valid = false;
            }
            if (password.length() == 0) {
                error.setPasswordError("Password can't be blank!");
                valid = false;
            }
            if (valid) {
               
                RegistrationDAO dao = new RegistrationDAO();
                SHA256 sha = new SHA256();
                String pass = sha.toHexString(sha.getSHA(password));
                String role = dao.checkLogin(email, pass);
                if (role.equals("failed")) {
                    request.setAttribute("ERROR", "Invalid email or password");
                } else {
                    HttpSession session = request.getSession();
                    String name = dao.loadNameMember(email);
                    session.setAttribute("EMAIL", email);
                    session.setAttribute("NAME", name);
                    session.setAttribute("ROLE", role);

                    if (role.equals("admin")) {
                        url = ADMIN;
                    } else if (role.equals("member")) {
                        url = USER;
                    } else {
                        url = ERROR;
                        request.setAttribute("ERROR", "Your role is invalid");
                    }
                }
               
                
                
            } else {
                url = INVALID;
                request.setAttribute("INVALID", error);
            }

        } catch (Exception e) {
            log("ERROR at SignInController" + e.getMessage());

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

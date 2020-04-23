/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trang
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SIGNUP = "SignUpController";
    private static final String SIGNIN = "SignInController";
    private static final String LOGOUT = "LogoutController";
    private static final String LOGIN = "signIn.jsp";
    private static final String POST = "PostController";
    private static final String POSTBLOG = "postBlog.jsp";
    private static final String COMMENT = "CommentController";
    private static final String LISTBLOG = "ListController";
    private static final String DETAILBLOG = "DetailController";
    private static final String DETAILADMINBLOG = "DetailAdminController";
    private static final String SEARCH = "SearchServlet";
    private static final String LISTSEARCHCONTENT = "ListOfSearchCController";
    private static final String LISTSEARCHTITLE = "ListSearchContentController";
    private static final String SEARCHCONTENT = "SearchContentController";
    private static final String SEARCHTITLE = "SearchTitleStatusController";
    private static final String LISTOFADMIN = "ListOfAdminController";
    private static final String DELETE = "DeleteController";
    private static final String APPROVAL = "ApprovalController";
    private static final String ACTIVE_ACCOUNT = "ActiveController";
    private static final String DELETEUSER = "UserController";

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
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE");
            if (role == null) {
                role = "";
            }
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = SIGNIN;
            } else if (action.equals("Register")) {
                url = SIGNUP;
            }else if (action.equals("LOGIN")) {
                url = LOGIN;
            }else if (action.equals("DeleteUser")) {
                url = DELETEUSER;
            }else if (action.equals("CREATE POST")) {
                url = POSTBLOG;
            } else if (action.equals("POST")) {
                url = POST;
            }else if (action.equals("Send")) {
                url = COMMENT;
            }else if (action.equals("Search")) {
                url = SEARCH;
            }else if (action.equals("Search By Content")) {
                url = LISTSEARCHCONTENT;
            }else if (action.equals("Search Content")) {
                url = SEARCHCONTENT;
            }else if (action.equals("Search By Title and Status")) {
                url = LISTSEARCHTITLE;
            }else if (action.equals("Search Title and Status")) {
                url = SEARCHTITLE;
            }else if (action.equals("List Blog")) {
                url = LISTBLOG;
            }else if (action.equals("Detail")) {
                url = DETAILBLOG;
            }else if (action.equals("DetailAdmin")) {
                url = DETAILADMINBLOG;
            }else if (action.equals("ListOfAdminController")) {
                url = LISTOFADMIN;
            }else if (action.equals("DELETE")) {
                url = DELETE;
            }else if (action.equals("Sent")) {
                url = ACTIVE_ACCOUNT;
            }else if (action.equals("APPROVAL")) {
                url = APPROVAL;
            }else if (action.equals("LOG OUT")) {
                url = LOGOUT;
            } else {
                request.setAttribute("ERROR", "Your action is invalid");
            }

        } catch (Exception e) {
            log("Error at MainController " + e.getMessage());
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

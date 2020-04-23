
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trangbtt.daos.ArticleDAO;
import trangbtt.daos.CommentDAO;
import trangbtt.dtos.ArticleDTO;
import trangbtt.dtos.CommentDTO;
import trangbtt.dtos.CommentErrorObject;

/**
 *
 * @author trang
 */
public class CommentController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String FORWARD = "signIn.jsp";
    private static final String SUCCESS = "detailBlog.jsp";
    private static final String INVALID = "detailBlog.jsp";

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
            String content = request.getParameter("txtComment");
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("EMAIL");
            String idBlog = request.getParameter("id");
            CommentDAO daoComment = new CommentDAO();
            ArticleDAO dao = new ArticleDAO();
            CommentErrorObject err = new CommentErrorObject();
            System.out.println(content);
            System.out.println(user);
            System.out.println(idBlog);
            System.out.println("role");

            boolean valid = true;

            if (content.length() == 0) {
                err.setContentError("Content can't be blank");
                valid = false;
            }
            if (user == null) {
                url = FORWARD;

            } else {
                String role  = daoComment.roleByComment(user);
                System.out.println(role);
                if (!role.equals("member")) {
                    err.setContentError("Your role is not valid. You must be member");
                    valid = false;
                }
                if (valid) {

                    if (daoComment.insertComment(content, user, idBlog)) {
                        List<ArticleDTO> result = dao.findByLikeIDBlog(idBlog);
                        List<CommentDTO> resultComment = daoComment.findByLikeIDInComment(idBlog);
                        url = SUCCESS;
                        request.setAttribute("INFO", result);
                        request.setAttribute("COMMENT", resultComment);
                    } else {
                        err.setContentError("Insert Comment Failed");
                    }
                } else {
                    url = INVALID;
                    request.setAttribute("INVALID", err);
                }
            }

        } catch (Exception e) {
            log("Error at CommentController " + e.getMessage());

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

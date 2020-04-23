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
import trangbtt.daos.ArticleDAO;
import trangbtt.dtos.ArticleDTO;
import trangbtt.dtos.ArticleErrorObject;

/**
 *
 * @author trang
 */
public class PostController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "postBlog.jsp";
    private static final String INVALID = "postBlog.jsp";

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
            String title = request.getParameter("txtTitle");
            String description = request.getParameter("txtDescription");
            String content = request.getParameter("txtContent");
            String author = request.getParameter("txtAuthor");
            ArticleErrorObject err = new ArticleErrorObject();
            ArticleDAO dao = new ArticleDAO();
            boolean valid = true;

            if (title.length() == 0) {
                err.setTitleError("Title can't be blank");
                valid = false;
            }
            if (description.length() == 0) {
                err.setDescriptionError("Description can't be blank");
                valid = false;
            }

            if (content.length() == 0) {
                err.setContentError("Content can't be blank");
                valid = false;
            }
            List<ArticleDTO> result = dao.loadListTitle();
            for (ArticleDTO dto : result) {
                if(title.equals(dto.getTitle())){
                    err.setTitleError("Title is exit!");
                    valid = false;
                }
            }
            if (valid) {

                if (dao.insertNewBlog(title, description, content, author)) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Insert Blog Failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", err);
            }

        } catch (Exception e) {
            log("Error at PostController " + e.getMessage());

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

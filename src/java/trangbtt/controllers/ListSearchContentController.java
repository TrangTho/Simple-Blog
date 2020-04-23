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
import trangbtt.dtos.LoadPage;

/**
 *
 * @author trang
 */
public class ListSearchContentController extends HttpServlet {

    private static final String SUCCESS = "searchByTS.jsp";
    private static final String ERROR = "error.jsp";

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
            ArticleDAO dao = new ArticleDAO();
            LoadPage loadPage = new LoadPage();
            String currentPage = request.getParameter("txtPage");
            int pageTotal = 0;
            int iCurrentPage = Integer.parseInt(currentPage);
            List<ArticleDTO> result = dao.loadListArticleDetail(iCurrentPage, 20);
            loadPage.setListActicle(result);
            int count = dao.countRowDetail(); 
            if (count % 20 == 0) {
                pageTotal = count / 20;
                loadPage.setTotalPage(pageTotal);
            } else {
                pageTotal = (count / 20) + 1;
                loadPage.setTotalPage(pageTotal);
            }

            if (result != null) {
                url = SUCCESS;
                request.setAttribute("LOADARTICLEADMIN", loadPage);

            } else {
                url = ERROR;
                request.setAttribute("ERROR", "List article is empty");
            }

        } catch (Exception e) {
            log("ERROR at ListOfControllerController" + e.getMessage());
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

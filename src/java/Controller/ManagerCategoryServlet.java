/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.CategoryDAO;

/**
 *
 * @author kuminhdey
 */
@WebServlet(name = "ManagerCategoryServlet", urlPatterns = {"/admin/ManagerCategoryServlet"})
public class ManagerCategoryServlet extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        String categoryid = request.getParameter("categoryid");
        String categoryname = request.getParameter("categoryname");
        String action = request.getParameter("action");
        System.out.println(action);
        CategoryDAO cateDAO = new CategoryDAO();
        if (action.equals("Add New")) {
            try (PrintWriter out = response.getWriter()) {
                Category cate = new Category(categoryid, categoryname);
                try {
                    if (cateDAO.addCategory(cate) > 0) {
                        response.sendRedirect("managercategory.jsp");
                    } else {
                        response.sendRedirect("managercategory.jsp");
                    }
                } catch (SQLException ex) {

                }
            }
        }
        if (action.equalsIgnoreCase("del")) {
            cateDAO.del(categoryid);
            response.sendRedirect("managercategory.jsp");
        }
        if (action.equalsIgnoreCase("edit")) {
            Category cate = cateDAO.findCategoryByID(categoryid);
            request.setAttribute("CATEGORY", cate);
            request.getRequestDispatcher("managercategory.jsp").forward(request, response);
        }
        if (action.equalsIgnoreCase("Update")) {
            cateDAO.update(categoryname, categoryid);
            response.sendRedirect("managercategory.jsp");
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

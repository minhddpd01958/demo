/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.CategoryDAO;
import model.Product;
import model.ProductDAO;

/**
 *
 * @author kuminhdey
 */
@WebServlet(name = "ManagerProductServlet", urlPatterns = {"/admin/ManagerProductServlet"})
public class ManagerProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    CategoryDAO cateDAO = new CategoryDAO();
    ProductDAO proDAO = new ProductDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String productid = request.getParameter("productid");
        String category = request.getParameter("category");
        String productname = request.getParameter("productname");
        String productprice = request.getParameter("productprice");
        String productimage = request.getParameter("productimage");
        String productdetail = request.getParameter("productdetail");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        try {
            request.setAttribute("CATEGORYNAME", cateDAO.getAllDanhMuc());
        } catch (SQLException ex) {
            Logger.getLogger(ManagerProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (action.equals("Add New")) {
            try (PrintWriter out = response.getWriter()) {
                Product pro = new Product(productid, new Category(category, ""), productname, productprice, productimage, productdetail);
                try {
                    if (proDAO.addProduct(pro) > 0) {
                        request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
                    }
                } catch (SQLException ex) {

                }
            }
        }
        if (action.equalsIgnoreCase("del")) {
            proDAO.del(productid);
             File file = new File(getServletContext().getRealPath("") + File.separator + "images\\" + productname + ".jpg");
            file.delete();
            response.sendRedirect("managerproduct.jsp");
        }
        if (action.equalsIgnoreCase("edit")) {
            Product pro = proDAO.findProductByID(productid);
            request.setAttribute("PRODUCT", pro);
            request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
        }
        if (action.equalsIgnoreCase("123")) {
            try {
                session.setAttribute("CATEGORYNAME", cateDAO.getAllDanhMuc());
                response.sendRedirect("managerproduct.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ManagerProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if (action.equalsIgnoreCase("Update")) {
            proDAO.update(category, productname, productprice, productimage, productdetail, productid);
            request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
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

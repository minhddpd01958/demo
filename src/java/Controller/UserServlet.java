/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import model.GioHangDAO;
import model.ProductDAO;
import model.User;
import model.UserDAO;

/**
 *
 * @author fpd
 */
@WebServlet(name = "categoryservlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        HttpSession session1 = request.getSession();
        UserDAO userDAO = new UserDAO();
        GioHangDAO gioDAO = new GioHangDAO();
        if (action.equalsIgnoreCase("add")) {

            try (PrintWriter out = response.getWriter()) {
                User user = new User("", username, email, pass);
                try {
                    if (userDAO.addUser(user) > 0) {
                        request.setAttribute("DANGKY", "OK");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        request.setAttribute("DANGKY", "FAIL");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (SQLException ex) {

                }
            }
        }
        if (action.equalsIgnoreCase("login")) {
            if (userDAO.checkLogin(username, pass)) {
                request.setAttribute("DANGNHAP", "OK");
                session1.setAttribute("DANGNHAP", "OK");
                int SoLuong = gioDAO.SoLuong(userDAO.findUserByUserName(username, pass).getId());
                session.setAttribute("SOLUONG", SoLuong);
                session.setAttribute("IDNGUOIDUNG", userDAO.findUserByUserName(username, pass).getId());
                session.setAttribute("USERNAME", userDAO.findUserByUserName(username, pass).getUserName());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("DANGNHAP", "FAIL");
                session1.setAttribute("DANGNHAP", "FAIL");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        if (action.equalsIgnoreCase("logout")) {
            session.invalidate();
            response.sendRedirect("");
        }
         if (action.equals("xemtheodanhmuc")) {
            String iddanhmuc = request.getParameter("iddanhmuc");
            request.setAttribute("IDDANHMUC", iddanhmuc);
            request.getRequestDispatcher("sanpham.jsp").forward(request, response);
        }
          if (action.equals("search")) {
              ProductDAO proDAO = new ProductDAO();
              request.setAttribute("SEARCH", request.getParameter("keyword"));
            request.getRequestDispatcher("sanpham.jsp").forward(request, response);
        }
//        if (action.equalsIgnoreCase("edit")) {
//            category ct1 = cateDAO.findCategoryByID(id);
//            request.setAttribute("CATEGORY", ct1);
//            request.getRequestDispatcher("category.jsp").forward(request, response);
//        }
//        if (action.equalsIgnoreCase("update")) {
//            cateDAO.update(name, id);
//            request.getRequestDispatcher("category.jsp").forward(request, response);
//        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

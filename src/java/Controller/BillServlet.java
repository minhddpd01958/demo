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
import model.User;
import model.UserDAO;
import model.bill;
import model.billDAO;

/**
 *
 * @author fpd
 */
@WebServlet(name = "cartservlet", urlPatterns = {"/CartServlet"})
public class BillServlet extends HttpServlet {

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
        int idnguoidung = Integer.parseInt(request.getParameter("idnguoidung"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        double price = Double.parseDouble(request.getParameter("price"));
        String action = request.getParameter("action");
        System.out.println(action);
        HttpSession session = request.getSession();
       billDAO billDAO = new billDAO();
        GioHangDAO gioDAO = new GioHangDAO();
        if (action.equals("Thanh Toán")) {
            try (PrintWriter out = response.getWriter()) {
                if(price == 0){
                    request.setAttribute("THEMBILL", "FAIL1");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                } else {
               bill bill = new bill(0, new User(idnguoidung+"", "","",""), name, phone, address, price,"");
                try {
                    if (billDAO.addBill(bill) > 0) {
                        request.setAttribute("THEMBILL", "OK");
                        gioDAO.del(idnguoidung);
                         int SoLuong = gioDAO.SoLuong((String) session.getAttribute("IDNGUOIDUNG"));
                        session.setAttribute("SOLUONG", SoLuong);
                        request.getRequestDispatcher("cart.jsp").forward(request, response);
                    } else {
                        request.setAttribute("THEMBILL", "FAIL");
                        request.getRequestDispatcher("cart.jsp").forward(request, response);
                    }
                } catch (SQLException ex) {

                }
                }
            }
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

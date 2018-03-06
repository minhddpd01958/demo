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
import model.GioHang;
import model.GioHangDAO;
import model.ProductDAO;

/**
 *
 * @author kuminhdey
 */
@WebServlet(name = "GioHangServlet", urlPatterns = {"/GioHangServlet"})
public class GioHangServlet extends HttpServlet {

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
        String idnguoidung = request.getParameter("idnguoidung");
        int idsanpham = Integer.parseInt(request.getParameter("idsanpham"));
        String action = request.getParameter("action");
        String page = request.getParameter("page");
        HttpSession session = request.getSession();
        GioHangDAO gioDAO = new GioHangDAO();
        if (action.equals("add")) {
            try (PrintWriter out = response.getWriter()) {
                GioHang gio = new GioHang(Integer.parseInt(idnguoidung), idsanpham, 1);
                try {
                    if (gioDAO.addSanPhamVaoGio(gio) > 0) {
                        int SoLuong = gioDAO.SoLuong((String) session.getAttribute("IDNGUOIDUNG"));
                        session.setAttribute("SOLUONG", SoLuong);
                        if(page.equals("detail")){
                              request.getRequestDispatcher("sanphamdetail.jsp").forward(request, response);
                        } else if(page.equals("index")){
                            response.sendRedirect("index.jsp");
                        }
                        else {
                            response.sendRedirect("sanpham.jsp");
                        } 
                    } else {
                        request.setAttribute("THEMGIO", "FAIL");
                         if(page.equals("detail")){
                            request.getRequestDispatcher("sanphamdetail.jsp").forward(request, response);
                        }else if(page.equals("index")){
                            response.sendRedirect("index.jsp");
                        } else {
                              response.sendRedirect("sanpham.jsp");
                          
                        } 
                    }
                } catch (SQLException ex) {
                }

            }
        }
        if (action.equals("tang")) {
            try (PrintWriter out = response.getWriter()) {
                GioHang gio = new GioHang(Integer.parseInt(idnguoidung), idsanpham, 1);
                try {
                    if (gioDAO.addSanPhamVaoGio(gio) > 0) {
                        int SoLuong = gioDAO.SoLuong((String) session.getAttribute("IDNGUOIDUNG"));
                        session.setAttribute("SOLUONG", SoLuong);
                        String s = SoLuong+"";
                        response.sendRedirect((String) session.getAttribute("PAGECART"));
                    } else {
                        request.setAttribute("THEMGIO", "FAIL");
                        response.sendRedirect((String) session.getAttribute("PAGECART"));
                    }
                } catch (SQLException ex) {
                }

            }
        }
        if (action.equals("giam")) {
            try (PrintWriter out = response.getWriter()) {
                GioHang gio = new GioHang(Integer.parseInt(idnguoidung), idsanpham, 1);
                try {
                   if (gioDAO.giamSoLuong(gio) > 0) {
                        int SoLuong = gioDAO.SoLuong((String) session.getAttribute("IDNGUOIDUNG"));
                        session.setAttribute("SOLUONG", SoLuong);
                        response.sendRedirect((String) session.getAttribute("PAGECART"));
                    }  else if (gioDAO.giamSoLuong(gio) == 0) {
                        gioDAO.delGio(Integer.parseInt(idnguoidung), idsanpham);
                        int SoLuong = gioDAO.SoLuong((String) session.getAttribute("IDNGUOIDUNG"));
                        session.setAttribute("SOLUONG", SoLuong);
                        response.sendRedirect((String) session.getAttribute("PAGECART"));
                    } else {
                        request.setAttribute("THEMGIO", "FAIL");
                        response.sendRedirect((String) session.getAttribute("PAGECART"));
                    }
                } catch (SQLException ex) {
                }

            }
        }
        if (action.equals("del")) {
            gioDAO.delGio(Integer.parseInt(idnguoidung), idsanpham);
            int SoLuong = gioDAO.SoLuong((String) session.getAttribute("IDNGUOIDUNG"));
            session.setAttribute("SOLUONG", SoLuong);
            response.sendRedirect((String) session.getAttribute("PAGECART"));
        }
         if (action.equals("xem")) {
            try {
                ProductDAO proDAO = new ProductDAO();
                for(int i = 0;i<proDAO.getAllProduct("").size();i++){
                  if(Integer.parseInt(proDAO.getAllProduct("").get(i).getProductid()) == idsanpham) {
                      session.setAttribute("SANPHAM",proDAO.getAllProduct("").get(i));
                  } 
                }
                // request.getRequestDispatcher("sanphamdetail.jsp").forward(request, response);
                  request.getRequestDispatcher("sanphamdetail.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GioHangServlet.class.getName()).log(Level.SEVERE, null, ex);
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

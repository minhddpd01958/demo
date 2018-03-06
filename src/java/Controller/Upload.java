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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.CategoryDAO;
import model.Product;
import model.ProductDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author kuminhdey
 */
@WebServlet(name = "Upload", urlPatterns = {"/admin/Upload"})
public class Upload extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

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
    private static final long serialVersionUID = 1L;

    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "images";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk 
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;

        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);
            String productid = "", category = "", productname = "", productprice = "", productimage = "", productdetail = "", action = "";
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (item.getFieldName().equals("productid")) {
                        productid = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("category")) {
                        category = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("productname")) {
                        productname = item.getString("UTF-8");

                    }
                    if (item.getFieldName().equals("productprice")) {
                        productprice = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("productimage")) {
                        productimage = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("productdetail")) {
                        productdetail = item.getString("UTF-8");
                    }
                    if (item.getFieldName().equals("action")) {
                        action = item.getString("UTF-8");
                    }
                }
                CategoryDAO cateDAO = new CategoryDAO();
                ProductDAO proDAO = new ProductDAO();
                try {
                    request.setAttribute("CATEGORYNAME", cateDAO.getAllDanhMuc());
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (action.equals("Add New")) {
                    String fileName = productname + ".jpg";
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            request.setAttribute("message", UPLOAD_DIRECTORY + "/" + fileName);
                        }
                    }
                    try (PrintWriter out = response.getWriter()) {
                        Product pro = new Product(productid, new Category(category, ""), productname, productprice, fileName, productdetail);
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
                    request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
                }
                if (action.equalsIgnoreCase("edit")) {
                    Product pro = proDAO.findProductByID(productid);
                    request.setAttribute("PRODUCT", pro);
                    request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
                }

                if (action.equalsIgnoreCase("123")) {
                    try {
                        request.setAttribute("CATEGORYNAME", cateDAO.getAllDanhMuc());
                    } catch (SQLException ex) {
                        Logger.getLogger(ManagerProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
                }
                if (action.equalsIgnoreCase("Update")) {
                    String fileName = productname+".jpg";
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            if(!item.getName().equals(""))
                                 item.write(storeFile);
                        }
                    }
                    proDAO.update(category, productname, productprice, fileName, productdetail, productid);
                    request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
                }
                if (action.equalsIgnoreCase("del")) {

                    proDAO.del(productid);
                    request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
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

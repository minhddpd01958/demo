/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kuminhdey
 */
public class ProductDAO {

    Connection conn = null;
    PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;

    public ProductDAO() {
        try {
            conn = new ConnectDB().getConnect();
            ps1 = conn.prepareStatement("select namecategory FROM category");    
            ps2 = conn.prepareStatement("SELECT product.productid, product.idcategory, product.productname, product.productprice, product.productimage, product.productdetail, category.idcategory, category.namecategory FROM product INNER JOIN category ON product.idcategory = category.idcategory WHERE product.productname LIKE ? ORDER BY product.productid DESC");
            ps4 = conn.prepareStatement("DELETE FROM `product` WHERE `product`.`productid` = ?");
            ps3 = conn.prepareStatement("UPDATE `product` SET `idcategory` = ?, `productname` = ?, `productprice` = ?, `productimage` = ?, `productdetail` = ? WHERE `product`.`productid` = ?;");
            ps = conn.prepareStatement("INSERT INTO `product` (`productid`, `idcategory`, `productname`, `productprice`, `productimage`, `productdetail`) VALUES (NULL, ?, ?, ?, ?, ?);");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addProduct(Product pro) throws SQLException {
        try {
            ps.setString(1, pro.getCategory().getCategoryid());
            ps.setString(2, pro.getProductname());
            ps.setString(3, pro.getProductprice());
            ps.setString(4, pro.getProductimage());
            ps.setString(5, pro.getProductdetail());
            if (ps.executeUpdate() == 1) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
            return -1;
        }
        return -1;
    }

    public List<Product> getAllProduct(String keyword) throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        ps2.setString(1, "%"+keyword+"%");
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            Product pro = new Product(rs.getString(1), new Category(rs.getString(2), rs.getString(8)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            listProduct.add(pro);
        }
        return listProduct;
    }

    public int del(String id) {
        int kq = 0;
        try {
            ps4.setString(1, id);
            kq = ps4.executeUpdate();
            return 1;
        } catch (SQLException ex) {

        }
        return kq;
    }

    public Product findProductByID(String id) {
        try {
            ProductDAO proDAO = new ProductDAO();
            for (int i = 0; i < proDAO.getAllProduct("").size(); i++) {
                Product pro = new Product();
                if (proDAO.getAllProduct("").get(i).getProductid().equals(id)) {
                    pro.setProductid(id);
                    pro.setCategory(proDAO.getAllProduct("").get(i).getCategory());
                    pro.setProductname(proDAO.getAllProduct("").get(i).getProductname());
                    pro.setProductprice(proDAO.getAllProduct("").get(i).getProductprice());
                    pro.setProductimage(proDAO.getAllProduct("").get(i).getProductimage());
                    pro.setProductdetail(proDAO.getAllProduct("").get(i).getProductdetail());
                    return pro;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int update(String categoryid, String productname, String productprice, String productimage, String productdetail, String productid) {
        int kq = 0;
        try {
            ps3.setString(1, categoryid);
            ps3.setString(2, productname);
            ps3.setString(3, productprice);
            ps3.setString(4, productimage);
            ps3.setString(5, productdetail);
            ps3.setString(6, productid);
            kq = ps3.executeUpdate();
        } catch (SQLException ex) {

        }
        return kq;
    }

    public static void main(String[] args) throws SQLException {
        ProductDAO pro = new ProductDAO();
        System.out.println(pro.getAllProduct(""));
    }
}

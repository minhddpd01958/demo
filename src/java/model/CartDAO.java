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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kuminhdey
 */
public class CartDAO {

    Connection conn = null;
    PreparedStatement ps, ps1, ps2, ps3;

    public CartDAO() {
        try {
            conn = new ConnectDB().getConnect();
            ps1 = conn.prepareStatement("SELECT product.productid, product.productname, giohang.SoLuong, product.productprice as DonGia, (giohang.SoLuong * product.productprice) AS Gia, product.productimage FROM product INNER JOIN giohang ON giohang.IDsanpham = product.productid INNER JOIN `user` ON giohang.IDnguoidung = `user`.ID WHERE giohang.IDnguoidung = ?");
            ps2 = conn.prepareStatement("DELETE FROM `category` WHERE `category`.`idcategory` = ?");
            ps3 = conn.prepareStatement("UPDATE `category` SET `namecategory` = ? WHERE `category`.`idcategory` = ?;");
            ps = conn.prepareStatement("INSERT INTO `category` (`idcategory`, `namecategory`) VALUES (NULL, ?);");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Cart> getAllCart(String IDnguoidung) throws SQLException {
        List<Cart> listCart = new ArrayList<>();
        ps1.setString(1, IDnguoidung);
        ResultSet rs = ps1.executeQuery();
        while (rs.next()) {
            Cart cart = new Cart(rs.getInt(1),rs.getString(2),rs.getString(6), rs.getInt(3),rs.getDouble(4),rs.getDouble(5));
            listCart.add(cart);
        }
        return listCart;
    }

    public int del(String id) {
        int kq = 0;
        try {
            ps2.setString(1, id);
            kq = ps2.executeUpdate();
            return 1;
        } catch (SQLException ex) {

        }
        return kq;
    }

    
    public static void main(String[] args) {
        CartDAO us1 = new CartDAO();
        try {
            System.out.println(us1.getAllCart("48"));
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

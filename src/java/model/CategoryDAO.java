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
public class CategoryDAO {

    Connection conn = null;
    PreparedStatement ps, ps1, ps2, ps3;

    public CategoryDAO() {
        try {
            conn = new ConnectDB().getConnect();
            ps1 = conn.prepareStatement("SELECT * FROM category ORDER BY category.idcategory DESC");
            ps2 = conn.prepareStatement("DELETE FROM `category` WHERE `category`.`idcategory` = ?");
            ps3 = conn.prepareStatement("UPDATE `category` SET `namecategory` = ? WHERE `category`.`idcategory` = ?;");
            ps = conn.prepareStatement("INSERT INTO `category` (`idcategory`, `namecategory`) VALUES (NULL, ?);");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addCategory(Category cate) throws SQLException {
        try {
            ps.setString(1, cate.getCategoryname());
            if (ps.executeUpdate() == 1) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
            return -1;
        }
        return -1;
    }

    public List<Category> getAllDanhMuc() throws SQLException {
        List<Category> listDanhmuc = new ArrayList<>();
        ResultSet rs = ps1.executeQuery();
        while (rs.next()) {
            Category danhmuc = new Category(rs.getString(1), rs.getString(2));
            listDanhmuc.add(danhmuc);
        }
        return listDanhmuc;
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

    public Category findCategoryByID(String id) {
        try {
            CategoryDAO ctd = new CategoryDAO();
            for (int i = 0; i < ctd.getAllDanhMuc().size(); i++) {
                if (ctd.getAllDanhMuc().get(i).getCategoryid().equals(id)) {
                    Category ct = new Category();
                    ct.setCategoryid(id);
                    ct.setCategoryname(ctd.getAllDanhMuc().get(i).getCategoryname());
                    return ct;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int update(String name, String id) {
        int kq = 0;
        try {
            ps3.setString(2, id);
            ps3.setString(1, name);
            kq = ps3.executeUpdate();
        } catch (SQLException ex) {

        }
        return kq;
    }

    public static void main(String[] args) {
        CategoryDAO us1 = new CategoryDAO();
        System.out.println(us1.findCategoryByID("32"));

    }
}

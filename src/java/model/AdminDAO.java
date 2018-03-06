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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kuminhdey
 */
public class AdminDAO {

    Connection conn = null;
    PreparedStatement ps;

    public AdminDAO() {
        try {
            conn = new ConnectDB().getConnect();
            ps = conn.prepareStatement("SELECT * FROM login");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkLogin(String u, String p) {
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (u.equalsIgnoreCase(rs.getString(1))  && p.equalsIgnoreCase(rs.getString(2))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public static void main(String[] args) {
       
        AdminDAO ad = new AdminDAO();
        System.out.println(ad.checkLogin("admin", "admin1"));
    }
}

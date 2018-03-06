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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kuminhdey
 */
public class UserDAO {
    Connection conn = null;
    PreparedStatement ps, ps1, ps2,ps3;

    public UserDAO() {
        try {
            conn = new ConnectDB().getConnect();
            ps1 = conn.prepareStatement("select * from user ORDER BY ID DESC");
            ps2 = conn.prepareStatement("delete from user where ID = ?");
             ps3 = conn.prepareStatement("UPDATE `user` SET `PassWord` = ? WHERE `user`.`ID` = ?;");
            ps = conn.prepareStatement("INSERT INTO `user` (`ID`, `UserName`, `Email`, `PassWord`) VALUES (NULL, ?, ?, ?);");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addUser(User user) throws SQLException {
        try {
           ps.setString(1, user.getUserName());
           ps.setString(2, user.getUserEmail());
           ps.setString(3, user.getUserPass());
            if (ps.executeUpdate() == 1) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
            return -1;
        }
        return -1;
    }

    
    public List<User> getAllUser() throws SQLException {
        List<User> listUser = new ArrayList<>();
        try {
        ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
                    listUser.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error:"+e.toString());
        }
        return listUser;
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

    public User findUserByID(String id) {
        try {
            UserDAO ud = new UserDAO();
            for (int i = 0; i < ud.getAllUser().size(); i++) {               
                if(ud.getAllUser().get(i).getId().equals(id)){
                    User user = new User();
                    user.setId(id);
                    user.setUserName(ud.getAllUser().get(i).getUserName());
                    user.setUserEmail(ud.getAllUser().get(i).getUserEmail());
                    user.setUserPass(ud.getAllUser().get(i).getUserPass());
                    return user; 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public User findUserByUserName(String Username, String PassWord) {
        try {
            UserDAO ud = new UserDAO();
            for (int i = 0; i < ud.getAllUser().size(); i++) {               
                if(ud.getAllUser().get(i).getUserName().equals(Username) || ud.getAllUser().get(i).getUserEmail().equals(Username) && ud.getAllUser().get(i).getUserPass().equals(PassWord)){
                    User user = new User();
                    user.setId(ud.getAllUser().get(i).getId());
                    user.setUserName(ud.getAllUser().get(i).getUserName());
                    user.setUserEmail(ud.getAllUser().get(i).getUserEmail());
                    user.setUserPass(ud.getAllUser().get(i).getUserPass());
                    return user; 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public int update(String pass, String id){
int kq = 0;
        try {
            ps3.setString(2, id);
            ps3.setString(1, pass);
            kq = ps3.executeUpdate();
        } catch (SQLException ex) {
            
        }
        return kq;
    }
     
     public boolean checkLogin(String u, String p) {
        try {
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                if (u.equalsIgnoreCase(rs.getString(2)) || u.equalsIgnoreCase(rs.getString(3)) ) {
                    if(p.equalsIgnoreCase(rs.getString(4))){
                         return true;
                    }
                }
            }
        } catch (SQLException ex) {
        }
        return false;
    }
     
    public static void main(String[] args) {

            UserDAO us = new UserDAO();
            System.out.println(us.checkLogin("kuminhdey9669@gmail.com", "12344321"));
        
    }
    
   
}

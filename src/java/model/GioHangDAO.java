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
public class GioHangDAO {

    Connection conn = null;
    PreparedStatement ps, ps1, ps2, ps3, ps4,ps5;

    public GioHangDAO() {
        try {
            conn = new ConnectDB().getConnect();
            ps1 = conn.prepareStatement("SELECT * FROM `giohang` WHERE IDnguoidung = ?");
            ps4 = conn.prepareStatement("SELECT * FROM `giohang` WHERE IDnguoidung = ? AND IDsanpham = ?");
            ps2 = conn.prepareStatement("DELETE FROM giohang WHERE IDnguoidung = ?");
            ps5 = conn.prepareStatement("DELETE FROM giohang WHERE IDnguoidung = ? AND IDsanpham = ?");
            ps3 = conn.prepareStatement("UPDATE `giohang` SET `SoLuong` = ? WHERE IDnguoidung = ? AND IDsanpham = ?");
            ps = conn.prepareStatement("INSERT INTO `giohang` (`IDnguoidung`, `IDsanpham`, `SoLuong`) VALUES (?, ?, ?);");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addSanPhamVaoGio(GioHang gio) throws SQLException {
        try {
            ps4.setInt(1, gio.getIDnguoidung());
            ps4.setInt(2, gio.getIDsanpham());
            ResultSet rs = ps4.executeQuery();
            if (rs.next()) {
                ps3.setInt(1, (rs.getInt(3) + 1));
                ps3.setInt(2, gio.getIDnguoidung());
                ps3.setInt(3, gio.getIDsanpham());
                ps3.executeUpdate();
                return 2;
            } else {
                ps.setInt(1, gio.getIDnguoidung());
                ps.setInt(2, gio.getIDsanpham());
                ps.setInt(3, gio.getSoLuong());
                if (ps.executeUpdate() == 1) {
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
            return -1;
        }
        return -1;
    }
      public int giamSoLuong(GioHang gio) throws SQLException {
        try {
            ps4.setInt(1, gio.getIDnguoidung());
            ps4.setInt(2, gio.getIDsanpham());
            ResultSet rs = ps4.executeQuery();
            if (rs.next()) {
                if(rs.getInt(3) == 1){
                    return 0;
                } else {
                ps3.setInt(1, (rs.getInt(3) - 1));
                ps3.setInt(2, gio.getIDnguoidung());
                ps3.setInt(3, gio.getIDsanpham());
                ps3.executeUpdate();
                return 1;
                }  
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
            return -1;
        }
        return -1;
    }

    public List<GioHang> getAllGioHang(String IDnguoidung) throws SQLException {
        List<GioHang> listGioHang = new ArrayList<>();
        try {
            ps1.setString(1, IDnguoidung);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                GioHang gio = new GioHang(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                listGioHang.add(gio);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
        }
        return listGioHang;
    }

    public int del(int IDnguoidung) {
        int kq = 0;
        try {
            ps2.setInt(1, IDnguoidung);
            kq = ps2.executeUpdate();
            return 1;
        } catch (SQLException ex) {

        }
        return kq;
    }
    public int delGio(int IDnguoidung, int IDsanpham) {
        int kq = 0;
        try {
            ps5.setInt(1, IDnguoidung);
            ps5.setInt(2, IDsanpham);
            kq = ps5.executeUpdate();
            return 1;
        } catch (SQLException ex) {

        }
        return kq;
    }
    public int SoLuong(String userid) {
        int kq = 0;
        try {
            GioHangDAO gioDAO = new GioHangDAO();
            
            for(int i = 0; i < gioDAO.getAllGioHang(userid).size();i++ ){
                kq = kq + gioDAO.getAllGioHang(userid).get(i).getSoLuong();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GioHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public User findUserByID(String id) {
        try {
            UserDAO ud = new UserDAO();
            for (int i = 0; i < ud.getAllUser().size(); i++) {
                if (ud.getAllUser().get(i).getId().equals(id)) {
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

    public int update(String pass, String id) {
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
                if (u.equalsIgnoreCase(rs.getString(2)) && p.equalsIgnoreCase(rs.getString(4))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public static void main(String[] args) {
GioHangDAO gd = new GioHangDAO();
        System.out.println(gd.delGio(48, 34));
    }

}

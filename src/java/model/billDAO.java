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
public class billDAO {

    Connection conn = null;
    PreparedStatement ps, ps1, ps2, ps3;

    public billDAO() {
        try {
            conn = new ConnectDB().getConnect();
            ps1 = conn.prepareStatement("SELECT bill.mahoadon, bill.idnguoidung, `user`.UserName, bill.tennguoinhan, bill.sodienthoai, bill.diachi, bill.tongtien, bill.trangthai FROM bill INNER JOIN `user` ON bill.idnguoidung = `user`.ID ORDER BY bill.mahoadon DESC");
            ps2 = conn.prepareStatement("delete from bill where bill.mahoadon = ?");
            ps3 = conn.prepareStatement("UPDATE bill SET trangthai = 'V' WHERE mahoadon = ?");
            ps = conn.prepareStatement("INSERT INTO `bill` (`mahoadon`, `idnguoidung`, `tennguoinhan`, `sodienthoai`, `diachi`, `tongtien`,`trangthai`) VALUES (NULL, ?, ?, ?, ?, ?,?);");
        } catch (SQLException ex) {
            Logger.getLogger(billDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addBill(bill bill) throws SQLException {
        try {
            ps.setString(1, bill.getNguoiDung().getId());
            ps.setString(2, bill.getTenNguoiNhan());
            ps.setString(3, bill.getSoDienThoai());
            ps.setString(4, bill.getDiaChi());
            ps.setDouble(5, bill.getTongTien());
            ps.setString(6, "X");
            if (ps.executeUpdate() == 1) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
            return -1;
        }
        return -1;
    }

    public List<bill> getAllBill() throws SQLException {
        List<bill> listBill = new ArrayList<>();
        try {
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
               bill bill = new bill(rs.getInt(1), new User(rs.getString(2),rs.getString(3),"",""), rs.getString(4), rs.getString(5), rs.getString(6),rs.getDouble(7),rs.getString(8));
                listBill.add(bill);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
        }
        return listBill;
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

 public int confirm(String id) {
        int kq = 0;
        try {
            ps3.setString(1, id);
            kq = ps3.executeUpdate();
            return 1;
        } catch (SQLException ex) {

        }
        return kq;
    }



    public static void main(String[] args) {

        try {
            billDAO us = new billDAO();
            System.out.println(us.getAllBill().get(0).getNguoiDung().getUserName());
        } catch (SQLException ex) {
            Logger.getLogger(billDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

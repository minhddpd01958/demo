/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author kuminhdey
 */
public class GioHang {

    private int IDnguoidung;
    private int IDsanpham;
    private int soLuong;

    public GioHang(int IDnguoidung, int IDsanpham, int soLuong) {
        this.IDnguoidung = IDnguoidung;
        this.IDsanpham = IDsanpham;
        this.soLuong = soLuong;
    }

    public int getIDnguoidung() {
        return IDnguoidung;
    }

    public void setIDnguoidung(int IDnguoidung) {
        this.IDnguoidung = IDnguoidung;
    }

    public int getIDsanpham() {
        return IDsanpham;
    }

    public void setIDsanpham(int IDsanpham) {
        this.IDsanpham = IDsanpham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public GioHang() {
    }

}

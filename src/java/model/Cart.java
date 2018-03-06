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
public class Cart {
    private int productid;
    private String productName;
    private String productImage;
    private int soLuong;
     private double donGia;
     private double tongGia;

    public Cart() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getTongGia() {
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }

    
    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public Cart(int productid, String productName, String productImage, int soLuong, double donGia, double tongGia) {
        this.productid = productid;
        this.productName = productName;
        this.productImage = productImage;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongGia = tongGia;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    
}

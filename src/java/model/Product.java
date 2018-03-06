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
public class Product {
    private String productid;
    private Category category;
    private String productname;
    private String productprice;
    private String productimage;
    private String productdetail;

    public Product() {
    }

    public Product(String productid, Category category, String productname, String productprice, String productimage, String productdetail) {
        this.productid = productid;
        this.category = category;
        this.productname = productname;
        this.productprice = productprice;
        this.productimage = productimage;
        this.productdetail = productdetail;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(String productdetail) {
        this.productdetail = productdetail;
    }

    
    
}

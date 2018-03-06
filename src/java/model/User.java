/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author TUNGDUONG
 */
public class User {
    private String id;
private String userName;
    private String userEmail;
    private String userPass;

    public User() {
    }

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public User(String id, String userName, String userEmail, String userPass) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

  

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    

    
}

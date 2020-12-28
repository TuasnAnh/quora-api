/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Account {

    private int aid;
    private int uid;
    private String email;
    private String role;
    private String loginStatus;

    public Account(int uid, String email, String role, String loginStatus) {
        this.uid = uid;
        this.email = email;
        this.role = role;
        this.loginStatus = loginStatus;
    }

    public Account(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public int getAid() {
        return aid;
    }

    public int getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public String getRole() {
        return role;
    }

}

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
public class User {

    private int uid;
    private String email;
    private String password;
    private String loginStatus;
    private String firstName;
    private String lastName;
    private String description;
    private String credential;
    private String school;
    private String degreeType;
    private String graduationYear;
    private String location;
    private String role;
    private String url;
    private String registerDate;

    public User(int uid, String firstName, String lastName, String description, String credential, String school, String degreeType, String graduationYear, String location, String role) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.credential = credential;
        this.school = school;
        this.degreeType = degreeType;
        this.graduationYear = graduationYear;
        this.location = location;
        this.role = role;
    }

    public User(int uid, String firstName, String lastName, String description, String credential, String school, String degreeType, String graduationYear, String location, String url, String registerDate) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.credential = credential;
        this.school = school;
        this.degreeType = degreeType;
        this.graduationYear = graduationYear;
        this.location = location;
        this.url = url;
        this.registerDate = registerDate;
    }

    public User(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public User(int uid, String email, String role, String loginStatus) {
        this.uid = uid;
        this.email = email;
        this.role = role;
        this.loginStatus = loginStatus;
    }

    public User(int uid, String role) {
        this.uid = uid;
        this.role = role;
    }

    public User(int uid, String email, String loginStatus) {
        this.uid = uid;
        this.email = email;
        this.loginStatus = loginStatus;
    }

    public int getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public String getCredential() {
        return credential;
    }

    public String getSchool() {
        return school;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public String getLocation() {
        return location;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.User;

/**
 *
 * @author Thao
 */
public interface AdminService {
    public boolean checkExistedEmail(String email);

    public int newUserAdmin(String firstName, String lastName, String email, String password);
    
    public int newTopicAdmin(String firstName, String lastName, String email, String password);
    
    public String getUserRoll(String email);    
    
    public boolean verifyEmail(String email);
    
    public boolean deleteAdmin(String email);
}

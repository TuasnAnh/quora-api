/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.User;

/**
 *
 * @author ADMIN
 */
public interface AccountService {

    public boolean checkExistedEmail(String email);

    public int insertUser(String firstName, String lastName, String email, String password);

    public String getUserRoll(String email);

    public User login(String email, String password);

    public boolean verifyEmail(String email);

}

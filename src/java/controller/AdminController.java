/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static controller.AccountController.accountService;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.AdminService;
import serviceImplement.AdminServiceImplement;

/**
 *
 * @author Thao
 */
public class AdminController {
    static AdminService adminService = new AdminServiceImplement();
    
    public static void newUserAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException, MessagingException {
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();//?
        String firstname = data.get("UserAdmin").getAsString();
        String lastname = data.get("UserAdmin").getAsString();
        Map<String, String> status = new LinkedHashMap<>();

        // check if existed email
        boolean isExisted = adminService.checkExistedEmail(email);
        if (isExisted) {
            status.put("status", "Duplicate Email");
        } else {
            int userId = adminService.newUserAdmin(firstname, lastname, email, password);
            System.out.println("new admin: " + userId);
            if (userId != -1) {
                status.put("status", "success");
                status.put("roll", adminService.getUserRoll(email));

                // store email in http session
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                // set session expire in 30 days
                session.setMaxInactiveInterval(30 * 24 * 60 * 60);

                new Thread(() -> {
                    try {
                        // send verify email
                        String verifyUrl = env.env.API_URL + "/verify?email=" + email;
                        utils.EmailUtils.sendEmail(email, verifyUrl);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        }

        String json = new Gson().toJson(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    public static void newTopicAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException, MessagingException {
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();//?
        String firstname = data.get("TopicAdmin").getAsString();
        String lastname = data.get("TopicAdmin").getAsString();
        Map<String, String> status = new LinkedHashMap<>();

        // check if existed email
        boolean isExisted = adminService.checkExistedEmail(email);
        if (isExisted) {
            status.put("status", "Duplicate Email");
        } else {
            int userId = adminService.newTopicAdmin(firstname, lastname, email, password);
            System.out.println("new admin: " + userId);
            if (userId != -1) {
                status.put("status", "success");
                status.put("roll", adminService.getUserRoll(email));

                // store email in http session
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                // set session expire in 30 days
                session.setMaxInactiveInterval(30 * 24 * 60 * 60);

                new Thread(() -> {
                    try {
                        // send verify email
                        String verifyUrl = env.env.API_URL + "/verify?email=" + email;
                        utils.EmailUtils.sendEmail(email, verifyUrl);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        }

        String json = new Gson().toJson(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    public static void deleteAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException, MessagingException {
        String email = data.get("email").getAsString();
        Map<String, String> status = new LinkedHashMap<>();

        // check if existed email
        boolean isExisted = adminService.checkExistedEmail(email);
        if (isExisted) {            
            boolean userId = adminService.deleteAdmin(email);
            System.out.println("Delete admin: " + userId);
            if (userId != false) {
                status.put("status", "success");
            }
        } else {
            status.put("status", "Delete failed.");
        }

        String json = new Gson().toJson(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import service.AccountService;
import serviceImplement.AccountServiceImplement;

/**
 *
 * @author ADMIN
 */
public class AccountController {

    static AccountService accountService = new AccountServiceImplement();

    public static void register(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException, MessagingException {
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();
        String firstname = data.get("firstname").getAsString();
        String lastname = data.get("lastname").getAsString();
        Map<String, String> status = new LinkedHashMap<>();

        // check if existed email
        boolean isExisted = accountService.checkExistedEmail(email);
        if (isExisted) {
            status.put("status", "Duplicate Email");
        } else {
            int userId = accountService.insertUser(firstname, lastname, email, password);
            System.out.println("new user: " + userId);
            if (userId != -1) {
                status.put("status", "success");
                status.put("roll", accountService.getUserRoll(email));

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

    public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        System.out.println("logout email: " + session.getAttribute("email"));
        session.invalidate();

        Map<String, String> message = new LinkedHashMap<>();
        message.put("message", "Logged out");
        String json = new Gson().toJson(message);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void login(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();
        User user = accountService.login(email, password);

        Map<String, String> message = new LinkedHashMap<>();
        message.put("status", user.getLoginStatus());
        if ("login success".equalsIgnoreCase(user.getLoginStatus())) {
            String userRoll = accountService.getUserRoll(user.getEmail());
            message.put("roll", userRoll);
            // store email in session
            HttpSession session = request.getSession();
            // expire in 30 days
            session.setMaxInactiveInterval(30 * 24 * 60 * 60);
            session.setAttribute("email", email);
        } else {
            System.out.println("Invalid account");
        }

        String json = new Gson().toJson(message);
        System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void verifyEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        Map<String, String> message = new LinkedHashMap<>();
        System.out.println(env.env.API_URL);
        if (email != null) {
            boolean isVerified = accountService.verifyEmail(email);
            if (isVerified) {
                message.put("status", "verified");
            } else {
                message.put("status", "failed verify");
            }
        } else {
            message.put("status", "Invalid parameter email");
        }

        String json = new Gson().toJson(message);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

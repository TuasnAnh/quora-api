/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import middleware.VerifyRequest;
import model.User;
import service.AdminService;
import serviceImplement.AdminServiceImplement;

/**
 *
 * @author Thao
 */
public class AdminController {

    static AdminService adminService = new AdminServiceImplement();

    public static void newUserAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException, MessagingException {
        if (VerifyRequest.verifyAdminManageRequest(request, response)) {
            String email = data.get("email").getAsString();//lay tu json
            String password = data.get("password").getAsString();
//            String role = data.get("role").getAsString();
            String firstname = "UserAdmin";
            String lastname = "UserAdmin";
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
                    status.put("role", adminService.getUserRoll(email));

                    new Thread(() -> {
                        try {
                            // send verify email
                            String verifyUrl = env.env.API_URL + "/verify?email=" + email;
//                            utils.EmailUtils.sendEmail(email, verifyUrl);
                            utils.EmailUtils.sendPass(email, password, verifyUrl);
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
    }

    public static void newTopicAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException, MessagingException {
        if (VerifyRequest.verifyAdminManageRequest(request, response)) {
            String email = data.get("email").getAsString();
            String password = data.get("password").getAsString();
            String firstname = "TopicAdmin";
            String lastname = "TopicAdmin";
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
                    status.put("role", adminService.getUserRoll(email));

                    new Thread(() -> {
                        try {
                            // send verify email
                            String verifyUrl = env.env.API_URL + "/verify?email=" + email;
//                            utils.EmailUtils.sendEmail(email, verifyUrl);
                            utils.EmailUtils.sendPass(email, password, verifyUrl);
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
    }

    public static void deleteAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException, MessagingException {
        if (VerifyRequest.verifyAdminManageRequest(request, response)) {
            int id = data.get("uid").getAsInt();
            Map<String, String> status = new LinkedHashMap<>();
            boolean userId = adminService.deleteAdmin(id);
            if (userId) {
                status.put("status", "success");
            } else {
                status.put("status", "Delete failed.");
            }
            String json = new Gson().toJson(status);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    public static void getAllUserAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (VerifyRequest.verifyAdminManageRequest(request, response)) {
            List<User> users = adminService.getAllUserAdmin();
            System.out.println("size: " + users.size());
            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).getEmail());
                jsonArray.add(users.get(i).getJSONObject());
            }
            String json = new Gson().toJson(jsonArray);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    public static void getAllTopicAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (VerifyRequest.verifyAdminManageRequest(request, response)) {
            List<User> users = adminService.getAllTopicAdmin();
            System.out.println("size: " + users.size());
            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).getEmail());
                jsonArray.add(users.get(i).getJSONObject());
            }
            String json = new Gson().toJson(jsonArray);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    public static void getSelectedAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        if (VerifyRequest.verifyAdminManageRequest(request, response)) {
            int id = data.get("uid").getAsInt();

            User user = adminService.getSelectedAdmin(id);

            String json = new Gson().toJson(user);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}

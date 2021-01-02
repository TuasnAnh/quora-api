/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.AdminController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.account.Register;

/**
 *
 * @author Thao
 */
@WebServlet(name = "NewUserAdmin", urlPatterns = {"/admin/insert-user-admin"})
public class NewUserAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        try {
            AdminController.newUserAdmin(request, response, data);
        } catch (MessagingException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.AccountService;
import serviceImplement.AccountServiceImplement;

/**
 *
 * @author ADMIN
 */
public class Authenticate {

    public static String authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AccountService accountService = new AccountServiceImplement();
        HttpSession session = request.getSession();
        if (session.getAttribute("email") == null) {
            String json = new Gson().toJson("Please login or register!");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            String email = session.getAttribute("email").toString();
            String role = accountService.getUserRoll(email);
            return role;
        }
        return null;
    }

}

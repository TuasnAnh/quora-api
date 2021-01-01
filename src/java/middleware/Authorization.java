/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class Authorization {

    public static boolean userAuthor(HttpServletResponse response, String role) throws IOException {
        if ("USER".equals(role)) {
            return true;
        } else {
            String json = new Gson().toJson("Permission Error: you are not user.");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        return false;
    }

    public static boolean userManageAuthor(HttpServletResponse response, String role) throws IOException {
        if ("USER_MANAGE".equals(role)) {
            return true;
        } else {
            String json = new Gson().toJson("Permission Error: you are not user manager.");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return false;
        }
    }
    
    public static boolean topicManageAuthor(HttpServletResponse response, String role) throws IOException {
        if ("TOPIC_MANAGE".equals(role)) {
            return true;
        } else {
            String json = new Gson().toJson("Permission Error: you are not topic manager.");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return false;
        }
    }
    
}

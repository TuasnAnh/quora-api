/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.VerifyRequest;
import serviceImplement.AccountServiceImplement;

/**
 *
 * @author ADMIN
 */
public class TopicController {

    static AccountServiceImplement accountService = new AccountServiceImplement();

    public static void getSuggestTopic(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        if (VerifyRequest.verifyUserRequest(request, response, accountService)) {
            String json = new Gson().toJson("get suggest topic");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
    
    public static void getFollowedTopic(HttpServletRequest request, HttpServletResponse response, JsonObject data) {
        
    }
}

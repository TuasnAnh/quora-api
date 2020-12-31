/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import middleware.Authenticate;
import middleware.Authorization;
import middleware.VerifyRequest;
import model.Topic;
import serviceImplement.*;
import service.*;

/**
 *
 * @author ADMIN
 */
public class TopicController {

    static AccountServiceImplement accountService = new AccountServiceImplement();

    static TopicService topicService = new TopicServiceImplement();

    public static void getSuggestTopic(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        if (VerifyRequest.verifyUserRequest(request, response, accountService)) {
            String json = new Gson().toJson("get suggest topic");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    public static void editTopic(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        int id = data.get("tid").getAsInt();
        String topicname = data.get("topicName").getAsString();
        String imageurl = data.get("topicImageUrl").getAsString();
        Map<String, String> status = new LinkedHashMap<>();

        boolean check = topicService.editTopic(id, topicname, imageurl);
        if (check) {
            status.put("noti", "Edit Topic Successfully");
        } else {
            status.put("noti", "Failed to edit Topic");
        }
        
        String json = new Gson().toJson(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void deleteTopic(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        int id = data.get("tid").getAsInt();
        
        Map<String, String> status = new LinkedHashMap<>();
        
        boolean check = topicService.deleteTopic(id);
        if(check) {
            status.put("noti", "Delete Topic Successfully");
        } 
        else {
            status.put("noti", "Failed to delete Topic");
        }
        String json = new Gson().toJson(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void insertTopic(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        String topicname = data.get("topicName").getAsString();
        String imageurl = data.get("topicImageUrl").getAsString();
        
        Map<String, String> status = new LinkedHashMap<>();
        
        boolean check = topicService.checkExistedTopic(topicname);
        if(check){
            status.put("noti", "Duplicated TopicName");
        }
        else {
            int check2= topicService.insertTopic(topicname,imageurl);
            if(check2==1){
                status.put("noti","Add Topic Successfully");
            }
            else{
                status.put("noti","Failed to add Topic");
            }
        }
        String json = new Gson().toJson(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void getAllTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Topic> topics = topicService.getAllTopic();
        System.out.println("size: " + topics.size());
        JsonArray jsonArray = new JsonArray();
        for (int i = 0 ; i< topics.size(); i++){
            System.out.println(topics.get(i).getTopicName());
            jsonArray.add(topics.get(i).getJSONObject());
        }
        String json = new Gson().toJson(jsonArray);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void getSelectedTopic(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        int id= data.get("tid").getAsInt();
        
        Topic topic = topicService.getSelectedTopic(id);
        
        String json = new Gson().toJson(topic);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

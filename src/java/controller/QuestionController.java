package controller;

import com.google.gson.Gson;
import static controller.QuestionController.questionService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;
import service.QuestionService;
import serviceImplement.QuestionServiceImplement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class QuestionController {

    static QuestionService questionService = new QuestionServiceImplement();

    public static void getQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int qid = Integer.parseInt(request.getParameter("qid"));
        Question question = questionService.getQuestion(qid);
        String json = new Gson().toJson(question);
        System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

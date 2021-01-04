/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import static controller.AnswerController.answerService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Answer;
import service.AnswerService;
import serviceImplement.AnswerServiceImplement;

/**
 *
 * @author ADMIN
 */
public class AnswerController {

    static AnswerService answerService = new AnswerServiceImplement();

    public static void deleteAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int aid = Integer.parseInt(request.getParameter("aid"));
        answerService.deleteAnswer(aid);
        response.sendRedirect("http://localhost:38940/quora-admin-client/admin/user-manage/report.jsp");
    }

    public static void getAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int aid = Integer.parseInt(request.getParameter("aid"));
        Answer answer = answerService.getAnswer(aid);
        String json = new Gson().toJson(answer);
        System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

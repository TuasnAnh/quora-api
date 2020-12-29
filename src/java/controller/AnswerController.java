/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.AnswerController.answerService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        response.sendRedirect("http://localhost:8080/quora-admin-client/admin/user-manage/report/report.jsp");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Report;
import service.ReportService;
import serviceImplement.ReportServiceImplement;

/**
 *
 * @author zilidazn
 */
public class ReportController {

    static ReportService reportService = new ReportServiceImplement();

    public static void getReports(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Report> reports = reportService.getReports();
        String json = new Gson().toJson(reports);
        System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void deleteReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int aid = Integer.parseInt(request.getParameter("aid"));
        reportService.deleteReport(aid);
        response.sendRedirect("http://localhost:8080/quora-admin-client/admin/user-manage/report/report.jsp");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import model.Report;

/**
 *
 * @author zilidazn
 */
public interface ReportService {

    public ArrayList<Report> getReports();
    
    public boolean deleteReport(int aid);
}

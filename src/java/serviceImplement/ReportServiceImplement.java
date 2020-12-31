/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImplement;

import connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Report;
import service.ReportService;

/**
 *
 * @author zilidazn
 */
public class ReportServiceImplement implements ReportService {

    @Override
    public ArrayList<Report> getReports() {
        ArrayList<Report> reports = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("select aid, number from report");) {
            ResultSet rs = state1.executeQuery();
            while (rs.next()) {
                reports.add(new Report(rs.getInt("aid"), rs.getInt("number")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reports;
    }

    @Override
    public boolean deleteReport(int aid) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("delete from report where aid = ?");) {
            state1.setInt(1, aid);
            state1.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

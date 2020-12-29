/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImplement;

import connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import service.AnswerService;
import service.ReportService;

/**
 *
 * @author ADMIN
 */
public class AnswerServiceImplement implements AnswerService {

    @Override
    public boolean deleteAnswer(int aid) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("delete from answer where aid = ?");) {
            state1.setInt(1, aid);
            state1.executeUpdate();
            ReportService reportService = new ReportServiceImplement();
            reportService.deleteReport(aid);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}

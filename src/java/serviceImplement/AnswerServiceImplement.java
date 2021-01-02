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
import model.Answer;
import service.AnswerService;
import service.ReportService;

/**
 *
 * @author ADMIN
 */
public class AnswerServiceImplement implements AnswerService {

    @Override
    public boolean deleteAnswer(int aid) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement state1 = connection.prepareStatement("select uid from answer where aid = ?");
            state1.setInt(1, aid);
            ResultSet rs = state1.executeQuery();
            rs.next();
            int uid = rs.getInt("uid");
            PreparedStatement state2 = connection.prepareStatement("delete from answer where aid = ?");
            state2.setInt(1, aid);
            state2.executeUpdate();
            PreparedStatement state3 = connection.prepareStatement("insert into notification (uid, notitype, content) values (?, ?, ?)");
            state3.setInt(1, uid);
            state3.setString(2, "ANNOUNCEMENT");
            state3.setString(3, "Your answer has been deleted");
            state3.executeUpdate();
            ReportService reportService = new ReportServiceImplement();
            reportService.deleteReport(aid);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Answer getAnswer(int aid) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement state1 = connection.prepareStatement("select * from answer where aid = ?");
            state1.setInt(1, aid);
            ResultSet rs = state1.executeQuery();
            rs.next();
            return new Answer(aid, rs.getInt("uid"), rs.getInt("qid"), rs.getString("content"), rs.getString("answertime"), rs.getInt("upvotes"), rs.getInt("downvotes"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

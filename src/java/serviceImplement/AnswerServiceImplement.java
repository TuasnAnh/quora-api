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

    ReportService rpService = new ReportServiceImplement();

    @Override
    public boolean deleteAnswer(int aid) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement state1 = connection.prepareStatement("select uid from answer where aid = ?");
//            // delete bookmark
//            PreparedStatement state4 = connection.prepareStatement("delete from bookmark where aid = ?");
//            state4.setInt(1, aid);
//            state4.executeUpdate();
//            // delete user_answer
//            PreparedStatement state5 = connection.prepareStatement("delete from user_answer where aid = ?");
//            state5.setInt(1, aid);
//            state5.executeUpdate();
//            // delete notification
//            PreparedStatement state6 = connection.prepareStatement("delete from notification where aid = ?");
//            state6.setInt(1, aid);
//            state6.executeUpdate();
            // update number answer in question
            PreparedStatement state8 = connection.prepareStatement("select qid from answer where aid = ?");
            state8.setInt(1, aid);
            ResultSet rs2 = state8.executeQuery();
            rs2.next();
            
            PreparedStatement state7 = connection.prepareStatement("update question set totalanswer = totalanswer - 1 where qid = ?");
            state7.setInt(1, rs2.getInt("qid"));
            state7.executeUpdate();
//            // delete report
//            rpService.deleteReport(aid);

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

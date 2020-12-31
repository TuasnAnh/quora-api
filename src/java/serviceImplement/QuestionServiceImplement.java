package serviceImplement;

import connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Question;
import service.QuestionService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class QuestionServiceImplement implements QuestionService {

    @Override
    public Question getQuestion(int qid) {
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement state1 = connection.prepareStatement("select * from question where qid = ?");
            state1.setInt(1, qid);
            ResultSet rs = state1.executeQuery();
            rs.next();
            return new Question(qid, rs.getInt("uid"), rs.getString("content"), rs.getString("qtime"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

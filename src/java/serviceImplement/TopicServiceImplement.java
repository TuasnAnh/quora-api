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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Topic;
import service.TopicService;

/**
 *
 * @author ADMIN
 */
public class TopicServiceImplement implements TopicService {

    @Override
    public int insertTopic(String topicname, String imageurl) {
        try ( Connection connection = JDBCConnection.getConnection();  PreparedStatement state = connection.prepareStatement("insert into topic (topicname,imageurl) values (?,?);");) {
            state.setString(1, topicname);
            state.setString(2, imageurl);
            int check = state.executeUpdate();
            if (check == 1) {
                System.out.println("Add topic successfully");
                return 1;
            } else {
                System.out.println("Failed to add topic");
                return 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TopicServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean editTopic(int id, String topicname, String imageurl) {
        try ( Connection connection = JDBCConnection.getConnection();  PreparedStatement state = connection.prepareStatement("update topic set topicname=?,imageurl=? where tid=?;");) {
            state.setString(1, topicname);
            state.setString(2, imageurl);
            state.setInt(3, id);
            int check = state.executeUpdate();
            if (check == 1) {
                System.out.println("Edit topic successfully");
                return true;
            } else {
                System.out.println("Failed to edit topic");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TopicServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteTopic(int id) {
        try ( Connection connection = JDBCConnection.getConnection();  PreparedStatement state = connection.prepareStatement("delete from topic where tid=?;");) {
            state.setInt(1, id);
            int check = state.executeUpdate();
            if (check == 1) {
                System.out.println("Delete topic successfully");
                return true;
            } else {
                System.out.println("Failed to delete topic");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TopicServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Topic> getAllTopic() {
        List < Topic > topics = new ArrayList < > ();

        try (Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from topic");) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("tid");
                String topicname = rs.getString("topicname");
                String imageurl = rs.getString("imageurl");
                topics.add(new Topic(id, topicname, imageurl));
            }
        } catch (SQLException e) 
        {}
        return topics;
    }

    @Override
    public Topic getSelectedTopic(int id) {
        Topic topic=null;
        try (Connection connection = JDBCConnection.getConnection();
            PreparedStatement state = connection.prepareStatement("select * from topic where tid=?");) {
            state.setInt(1, id);

            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                String topicname = rs.getString("topicname");
                String imageurl = rs.getString("imageurl");
                topic = new Topic(id, topicname, imageurl);
            }
        } catch (SQLException e) 
        {}
        return topic;
    }

    @Override
    public boolean checkExistedTopic(String topicname) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state = connection.prepareStatement("select * from topic where topicname = ?");) {

            state.setString(1, topicname);

            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

}

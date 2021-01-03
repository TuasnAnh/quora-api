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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Topic;
import model.User;
import service.AdminService;
//import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Thao
 */
public class AdminServiceImplement implements AdminService{
    

    @Override
    public boolean checkExistedEmail(String email) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("select * from user where email = ?");) {

            state1.setString(1, email);

            ResultSet rs = state1.executeQuery();
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

    @Override
    public int newUserAdmin(String firstName, String lastName, String email, String password) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("insert into user (email, password, first_name, last_name, role, register_date) values (?, ?, ?, ?, ?, ?);");
                PreparedStatement state2 = connection.prepareStatement("SELECT LAST_INSERT_ID();");) {

            Date date = new java.util.Date();
            Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            state1.setString(1, email);
            state1.setString(2, password);
            state1.setString(3, "UserAdmin");
            state1.setString(4, "UserAdmin");
            state1.setString(5, "USER_MANAGE");
            state1.setTimestamp(6, timestamp);

            int check = state1.executeUpdate();
            if (check == 1) {
                ResultSet rs = state2.executeQuery();

                if (rs.next()) {
                    System.out.println("new admin: " + rs.getInt("LAST_INSERT_ID()"));
                    return rs.getInt("LAST_INSERT_ID()");
                } else {
                    System.out.println("failed get admin id");
                }

            } else {
                System.out.println("Failed insert admin");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public int newTopicAdmin(String firstName, String lastName, String email, String password) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("insert into user (email, password, first_name, last_name, role, register_date) values (?, ?, ?, ?, ?, ?);");
                PreparedStatement state2 = connection.prepareStatement("SELECT LAST_INSERT_ID();");) {

            Date date = new java.util.Date();
            Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            state1.setString(1, email);
            state1.setString(2, password);
            state1.setString(3, "TopicAdmin");
            state1.setString(4, "TopicAdmin");
            state1.setString(5, "TOPIC_MANAGE");//viet vao db?
            state1.setTimestamp(6, timestamp);

            int check = state1.executeUpdate();
            if (check == 1) {
                ResultSet rs = state2.executeQuery();

                if (rs.next()) {
                    System.out.println("new admin: " + rs.getInt("LAST_INSERT_ID()"));
                    return rs.getInt("LAST_INSERT_ID()");
                } else {
                    System.out.println("failed get admin id");
                }

            } else {
                System.out.println("Failed insert admin");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public String getUserRoll(String email) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement state = connection.prepareStatement("select role from user where email = ?");) {
            state.setString(1, email);

            ResultSet rs = state.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean verifyEmail(String email) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement state = connection.prepareStatement("update user set status = ? where email = ?");) {
            state.setString(1, "VERIFIED");
            state.setString(2, email);
            state.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(int id) {
        try ( Connection connection = JDBCConnection.getConnection();  PreparedStatement state = connection.prepareStatement("delete from user where uid=?;");) {
            state.setInt(1, id);
            int check = state.executeUpdate();
            if (check == 1) {
                System.out.println("Delete admin successfully");
                return true;
            } else {
                System.out.println("Failed to delete admin");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TopicServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<User> getAllUserAdmin() {
        List < User > users = new ArrayList < > ();

        try (Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where role = 'USER_MANAGE' ");) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("uid");
                String email = rs.getString("email");
                String password = rs.getString("password");
                users.add(new User(id, email, password, "VERIFIED", "USER_MANAGE"));
            }
        } catch (SQLException e) 
        {}
        return users;
    }

    @Override
    public User getSelectedAdmin(int id) {
        User user = null;
        try (Connection connection = JDBCConnection.getConnection();
            PreparedStatement state = connection.prepareStatement("select * from user where uid=?");) {
            state.setInt(1, id);

            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                String role = rs.getString("role");
                user = new User(id, role);
            }
        } catch (SQLException e) 
        {}
        return user;
    }
    
    @Override
    public List<User> getAllTopicAdmin() {
        List < User > users = new ArrayList < > ();

        try (Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where role = 'TOPIC_MANAGE' ");) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("uid");
                String email = rs.getString("email");
                String password = rs.getString("password");
                users.add(new User(id, email, password, "VERIFIED", "TOPIC_MANAGE"));
            }
        } catch (SQLException e) 
        {}
        return users;
    }
    
}

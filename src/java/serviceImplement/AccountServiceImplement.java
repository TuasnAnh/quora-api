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
import model.User;
import service.AccountService;

/**
 *
 * @author ADMIN
 */
public class AccountServiceImplement implements AccountService {

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
    public int insertUser(String firstName, String lastName, String email, String password) {

        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("insert into user (email, password, first_name, last_name, role, register_date) values (?, ?, ?, ?, ?, ?);");
                PreparedStatement state2 = connection.prepareStatement("SELECT LAST_INSERT_ID();");) {

            Date date = new java.util.Date();
            Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            state1.setString(1, email);
            state1.setString(2, password);
            state1.setString(3, firstName);
            state1.setString(4, lastName);
            state1.setString(5, "USER");
            state1.setTimestamp(6, timestamp);

            int check = state1.executeUpdate();
            if (check == 1) {
                ResultSet rs = state2.executeQuery();

                if (rs.next()) {
                    System.out.println("new user: " + rs.getInt("LAST_INSERT_ID()"));
                    return rs.getInt("LAST_INSERT_ID()");
                } else {
                    System.out.println("failed get user id");
                }

            } else {
                System.out.println("Failed insert user");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public String getUserRole(String email) {
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
    public User login(String email, String password) {

        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement state = connection.prepareStatement("select * from user where email = ?");) {
            state.setString(1, email);

            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    if (rs.getString("status").equals("VERIFIED")) {
                        return new User(rs.getInt("uid"), email, rs.getString("role"), "login success");
                    } else if (rs.getString("status").equals("NOT_VERIFY")) {
                        System.out.println("Email not verified");
                        return new User("account not verified");
                    } else if (rs.getString("status").equals("BANNED")) {
                        System.out.println("banned account");
                        return new User("banned account");
                    }
                } else {
                    System.out.println("Incorrect password");
                    return new User("incorrect password");
                }
            } else {
                System.out.println("Invalid email");
                return new User("email not found");
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
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("select uid, email, status from user where role = \"USER\"");) {
            ResultSet rs = state1.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("uid"), rs.getString("email"), rs.getString("status")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean banUser(int uid) {
        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement state = connection.prepareStatement("update user set status = ? where uid = ?");) {
            state.setString(1, "BANNED");
            state.setInt(2, uid);
            state.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int uid) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("delete from user where uid = ?");) {
            state1.setInt(1, uid);
            state1.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUser(int uid) {
        try (Connection connection = JDBCConnection.getConnection();
                PreparedStatement state1 = connection.prepareStatement("select first_name, last_name, description, credential, school, degreetype, graduation_year, location, url, register_date from user where role = \"USER\"");) {
            ResultSet rs = state1.executeQuery();
            if (rs.next()) {
                return new User(uid, rs.getString("first_name"), rs.getString("last_name"), rs.getString("description"), rs.getString("credential"), rs.getString("school"), rs.getString("degreetype"), rs.getString("graduation_year"), rs.getString("location"), rs.getString("url"), rs.getString("register_date"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

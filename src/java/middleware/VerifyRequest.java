/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class VerifyRequest {

    public static boolean verifyUserRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String role = Authenticate.authenticate(request, response);
        if (role != null && Authorization.userAuthor(response, role)) {
            return true;
        }
        return false;
    }

    public static boolean verifyUserManageRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String role = Authenticate.authenticate(request, response);
        if (role != null && Authorization.userManageAuthor(response, role)) {
            return true;
        }
        return false;
    }

    public static boolean verifyTopicManageRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String role = Authenticate.authenticate(request, response);
        if (role != null && Authorization.topicManageAuthor(response, role)) {
            return true;
        }
        return false;
    }

    public static boolean verifyAdminManageRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String role = Authenticate.authenticate(request, response);
        if (role != null && Authorization.adminManageAuthor(response, role)) {
            return true;
        }
        return false;
    }
}

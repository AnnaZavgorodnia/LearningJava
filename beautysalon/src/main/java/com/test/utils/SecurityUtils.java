package com.test.utils;

import com.test.config.SecurityConfig;
import com.test.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtils {

    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = request.getRequestURI();
        Set<Role> roles = SecurityConfig.getAllAppRoles();


        for (Role role : roles) {
            List<String> urlPatterns = SecurityConfig.getSecuredRoutesForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = request.getRequestURI();

        Set<Role> roles = SecurityConfig.getAllAppRoles();

        for (Role role : roles) {
            if (!request.isUserInRole(role.toString())) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getSecuredRoutesForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}

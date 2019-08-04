package com.test.utils;

import com.test.config.SecurityConfig;
import com.test.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtils {

    public static boolean isSecurityPage(String urlPattern) {
        Set<Role> roles = SecurityConfig.getAllAppRoles();


        for (Role role : roles) {
            List<String> urlPatterns = SecurityConfig.getSecuredRoutesForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(String urlPattern, Role userRole) {
        Set<Role> roles = SecurityConfig.getAllAppRoles();

        for (Role role : roles) {
            if (role != userRole) {
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

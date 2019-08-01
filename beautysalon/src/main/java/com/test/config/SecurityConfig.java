package com.test.config;

import com.test.model.entity.Role;

import java.util.*;

public class SecurityConfig {

    private static final Map<Role, List<String>> securedRoutes = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/app/userInfo");
        urlPatterns1.add("/app/masters");
        urlPatterns1.add("/app/me/appointments");

        securedRoutes.put(Role.CLIENT, urlPatterns1);

        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/app/userInfo");
        urlPatterns2.add("/app/all_appointments");

        securedRoutes.put(Role.ADMIN, urlPatterns2);

        List<String> urlPatterns3 = new ArrayList<>();

        urlPatterns3.add("/app/all_appointments");
        urlPatterns3.add("/app/userInfo");

        securedRoutes.put(Role.MASTER, urlPatterns3);
    }

    public static List<String> getSecuredRoutesForRole(Role role){
        return securedRoutes.get(role);
    }

    public static Set<Role> getAllAppRoles() {
        return securedRoutes.keySet();
    }
}

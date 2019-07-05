package com.training.security;

import com.training.entity.RoleType;
import com.training.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

public final class UserDetailsFactory {

    public UserDetailsFactory(){}

    public static MyUserDetails create(User user){
        return new MyUserDetails(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                roleToGrantedAuthorities(user.getRole())
        );
    }

    private static List<GrantedAuthority> roleToGrantedAuthorities(RoleType role){
        return Collections.singletonList(role);
    }
}

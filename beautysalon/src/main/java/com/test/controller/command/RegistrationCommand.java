package com.test.controller.command;

import com.test.model.entity.Role;
import com.test.model.entity.User;
import com.test.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(username == null && fullName == null && email == null && password == null) {
            return "/WEB-INF/views/registrationView.jsp";
        }

        User user = new User();

        user.setUsername(username);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.CLIENT);

        userService.createUser(user);

        return "redirect:/app/login";
    }
}

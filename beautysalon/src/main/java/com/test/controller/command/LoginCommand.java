package com.test.controller.command;

import com.test.model.entity.User;
import com.test.model.service.UserService;
import com.test.utils.AppUtils;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command  {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == null && password == null){
            if(request.getParameter("logout") != null) {
                request.setAttribute("logout", "logout");
            }
            return "/WEB-INF/views/loginView.jsp";
        }

        Optional<User> user = userService.getUserByUsername(username);

        //String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));

        if (!user.isPresent() || !user.get().getPassword().equals(password)) {

            String errorMessage = "Invalid Username or Password";

            request.setAttribute("errorMessage", errorMessage);

            return "/WEB-INF/views/loginView.jsp";
        }


        AppUtils.storeLoginedUser(request.getSession(), user.get());

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception ignored) { }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            return "redirect:"+requestUri;
        } else {
            return "redirect:/app/home";
        }
    }
}

package com.test.controller;

import com.test.controller.command.*;
import com.test.model.entity.User;
import com.test.model.service.AppointmentService;
import com.test.model.service.MasterService;
import com.test.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({ "/app/*" })
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public void init(){
        commands.put("users",
                new AllUsersCommand(new UserService()));
        commands.put("exception" , new ExceptionCommand());
        commands.put("home" , new HomeCommand());
        commands.put("login" , new LoginCommand(new UserService()));
        commands.put("logout" , new LogoutCommand());
        commands.put("masters", new AllMastersCommand(new MasterService()));
        commands.put("create_app", new CreateAppointmentCommand());
        commands.put("me/appointments", new AllUsersAppointmentsCommand(new AppointmentService()));
        commands.put("all_appointments", new AllAppointmentsCommand(new AppointmentService()));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*app/", "");
        Command command = commands.getOrDefault(path, new HomeCommand());
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(request.getContextPath() + page).forward(request, response);
        }
    }
}

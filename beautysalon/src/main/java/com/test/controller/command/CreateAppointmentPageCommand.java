package com.test.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CreateAppointmentPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/views/create_app.jsp";
    }
}

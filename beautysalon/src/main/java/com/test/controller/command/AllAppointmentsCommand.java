package com.test.controller.command;

import com.test.model.entity.Appointment;
import com.test.model.entity.Role;
import com.test.model.entity.User;
import com.test.model.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllAppointmentsCommand implements Command {

    private final AppointmentService appointmentService;

    public AllAppointmentsCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("loginedUser");
        List<Appointment> appointments = user.getRole() == Role.MASTER
                ? appointmentService.getAllAppointmentsByMaster_Username(user.getUsername())
                : appointmentService.findAll();
        request.setAttribute("appointments", appointments);
        return "/WEB-INF/views/allAppointmentsView.jsp";
    }
}

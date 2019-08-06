package com.test.controller.command;

import com.test.model.entity.Position;
import com.test.model.service.SalonServicesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateMasterPageCommand implements Command {

    private final SalonServicesService servicesService;

    public CreateMasterPageCommand(SalonServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<String> roleNames = Stream.of(Position.values())
                .map(Position::name)
                .collect(Collectors.toList());
        request.setAttribute("positions", roleNames);
        request.setAttribute("services", servicesService.findAll());

        return "/WEB-INF/views/createMasterView.jsp";
    }
}

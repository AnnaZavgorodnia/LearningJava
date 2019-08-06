package com.test.controller.command;

import com.test.model.entity.Master;
import com.test.model.entity.Position;
import com.test.model.entity.Role;
import com.test.model.entity.Service;
import com.test.model.exception.UserAlreadyExistsException;
import com.test.model.service.MasterService;
import com.test.model.service.SalonServicesService;
import com.test.utils.RegistrationUtils;
import com.test.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateMasterCommand implements Command {

    private final MasterService masterService;
    private final SalonServicesService servicesService;

    public CreateMasterCommand(MasterService masterService, SalonServicesService servicesService) {
        this.servicesService = servicesService;
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String instagram = request.getParameter("instagram");
        String email = request.getParameter("email");
        String position = request.getParameter("position");
        String[] selected = request.getParameterValues("services");


        List<String> roleNames = Stream.of(Position.values())
                .map(Position::name)
                .collect(Collectors.toList());
        request.setAttribute("positions", roleNames);
        request.setAttribute("services", servicesService.findAll());

        request.setAttribute("module", "add_master");

        if(username == null || firstName == null || lastName == null ||
                password == null || instagram == null || email == null ||
                position == null || selected == null){
            return "/WEB-INF/views/createMasterView.jsp";
        }

        Part filePart = null;
        try {
            filePart = request.getPart("file");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }


        if(!RegistrationUtils.checkIfValid(
                request, username, firstName, lastName,
                email, password, instagram) || filePart == null){

            RegistrationUtils.setUserAttributes(request, username, firstName,
                    lastName, email, password, instagram);

            return "/WEB-INF/views/createMasterView.jsp";

        }

        String imagePath = username + getSubmittedFileExt(filePart);

        Master master = new Master();
        master.setUsername(username);
        master.setFullName(firstName+" "+lastName);
        master.setPassword(SecurityUtils.getHashedPassword(password));
        master.setRole(Role.MASTER);
        master.setEmail(email);
        master.setInstagram(instagram);
        master.setPosition(Position.valueOf(position));
        master.setImagePath(imagePath);

        List<Service> services = Arrays.stream(selected)
                                        .map(Long::valueOf)
                                        .map(Service::new)
                                        .collect(Collectors.toList());

        master.setServices(services);

        try{
            masterService.create(master);
        } catch (UserAlreadyExistsException e){
            request.setAttribute("userExistsError"," ");
            RegistrationUtils.setUserAttributes(request, username, firstName,
                    lastName, email, password, instagram);
            return "/WEB-INF/views/createMasterView.jsp";
        }

        saveImage(request, imagePath, filePart);

        //todo redirect to all masters
        return "/WEB-INF/views/createMasterView.jsp";
    }

    private void saveImage(HttpServletRequest request,String imagePath, Part filePart){

        File uploads = new File(request.getServletContext().getRealPath("/")+"masters");

        File file = new File(uploads, imagePath);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getSubmittedFileExt(Part part){
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('.')).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}

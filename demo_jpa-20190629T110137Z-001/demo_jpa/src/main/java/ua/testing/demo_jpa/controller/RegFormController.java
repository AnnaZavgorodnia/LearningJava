package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class RegFormController {

    private final UserService userService;

    @Autowired
    public RegFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/reg_user", method = RequestMethod.POST)
    public void registrationFormController(User user){
        log.info("{}", user);
        userService.saveNewUser(user);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

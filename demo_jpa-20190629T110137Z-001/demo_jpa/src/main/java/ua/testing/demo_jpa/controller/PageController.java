package ua.testing.demo_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String mainPage(){ return "index.html"; }

    @RequestMapping("/api")
    public String regPage(){
        return "user/index.html";
    }

    @RequestMapping("/all_users")
    public String userPage(){
        return "users/index.html";
    }
}

package com.salon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage(Model model, Principal principal){
        model.addAttribute("module","index");
        return "index";
    }

    @RequestMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model,
                            Principal principal){
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        model.addAttribute("module", "login");
        return "login";
    }

    @GetMapping("/registration")
    public String regPage(Model model, Principal principal){
        model.addAttribute("module", "registration");
        return "registration";
    }

    @GetMapping("/masters")
    public String mastersPage(Model model, Principal principal){
        model.addAttribute("module", "masters");
        return "masters";
    }

    @GetMapping("/masters/add_master")
    public String addMasterPage(Model model, Principal principal){
        model.addAttribute("module", "masters");
        return "add_master";
    }

    @GetMapping("/create_app/{id}")
    public String addAppointmentPage(@PathVariable Long id, Model model){
        model.addAttribute("masterId",id);
        return "create_app";
    }

    @GetMapping("/me/appointments")
    public String addMyAppPage(Model model){
        model.addAttribute("module","my_appointments");
        return "user_apps";
    }

}

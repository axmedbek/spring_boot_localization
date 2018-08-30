package com.axmedbek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("message","Hello Spring Boot");
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
}

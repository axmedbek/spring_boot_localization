package com.axmedbek.controller;

import com.axmedbek.dao.UserDao;
import com.axmedbek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    private final UserDao userDao;

    @Autowired
    public HomeController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/")
    public String homePage(Model model){
        List<User> users = userDao.findAll();
        model.addAttribute("users",users);
        model.addAttribute("user",new User());
        return "index";
    }

    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    public String addUserProcess(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }


}

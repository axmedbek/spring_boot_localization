package com.axmedbek.controller.api;

import com.axmedbek.dao.UserDao;
import com.axmedbek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public List<User> allUsers(){
        return userDao.findAll();
    }
}

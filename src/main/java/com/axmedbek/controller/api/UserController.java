package com.axmedbek.controller.api;

import com.axmedbek.dao.UserDao;
import com.axmedbek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/users")
    public List<User> allUsers(){
        return userDao.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") long id){
        Optional<User> user = userDao.findById(id);
        return user;
    }
}

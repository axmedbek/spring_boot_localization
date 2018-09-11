package com.axmedbek.utils;

import com.axmedbek.dao.UserDao;
import com.axmedbek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("helper")
public class Helper {

    private static UserDao userDao;

    @Autowired
    public Helper(UserDao userDao) {
        Helper.userDao = userDao;
    }

    public static User getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByUsername(auth.getName());
        return user;
    }
}

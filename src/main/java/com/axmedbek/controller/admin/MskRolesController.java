package com.axmedbek.controller.admin;

import com.axmedbek.dao.RoleDao;
import com.axmedbek.dao.UserDao;
import com.axmedbek.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MskRolesController {

    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public MskRolesController(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @RequestMapping("/msk/roles")
    public String mskRolePage(Model model){
        List<Role> roles = roleDao.findAll();
        model.addAttribute("roles",roles);
        return "admin/msk/msk_roles";
    }
}

package com.axmedbek.controller;

import com.axmedbek.dao.RoleDao;
import com.axmedbek.dao.UserDao;
import com.axmedbek.model.Role;
import com.axmedbek.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("userDaoRepository")
    private UserDao userDao;

    @Autowired
    @Qualifier("roleRepository")
    private RoleDao roleDao;

    @Autowired
    @Qualifier("passwordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/")
    public String homePage(Model model) {
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/user/add")
    @ResponseBody
    public ModelAndView addUserModal(@RequestParam("id") Long id) {
        Optional<User> user = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modals/user_add_edit_info");
        if (id == 0) {
            user = Optional.of(new User());
            modelAndView.addObject("action", "add");
        } else {
            user = userDao.findById(id);
            modelAndView.addObject("action", "edit");
        }
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String addUserProcess(@ModelAttribute User user, HttpServletRequest request) {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/images/");
        MultipartFile multipartFile = user.getFile();
        File uploadPathDir = new File(rootDirectory);
        if (!uploadPathDir.exists()){
            uploadPathDir.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        String fileExtension = multipartFile.getContentType().split("/")[1];
        if (fileName!= null && fileName.length() > 0){
            try {
                int random = (1000 + (int) (Math.random() * 9000));
                String imagePath = user.getName().toLowerCase() + random;
                Path path = Paths.get(rootDirectory + imagePath + "." + fileExtension);
                multipartFile.transferTo(new File(path.toString()));
                user.setImage(imagePath + "." + fileExtension);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleDao.findAll()));
        userDao.save(user);
        return "redirect:/";
    }

    @RequestMapping("/user/info")
    public ModelAndView infoUserModal(@RequestParam("id") Long id){
        ModelAndView modelAndView = new ModelAndView("modals/user_info");
        Optional<User> user = userDao.findById(id);
        modelAndView.addObject("name",user.get().getName());
        modelAndView.addObject("surname",user.get().getSurname());
        modelAndView.addObject("age",user.get().getAge());
        modelAndView.addObject("image",user.get().getImage());
        modelAndView.addObject("position",user.get().getPosition());
        modelAndView.addObject("description",user.get().getDescription());
        return modelAndView;
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }


    @DeleteMapping(value = "/user/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteUser(@PathVariable("id") Long id) {
        userDao.deleteById(id);
        Map<String, Object> maps = new HashMap<>();
        maps.put("status", "ok");
        return maps;
    }

}

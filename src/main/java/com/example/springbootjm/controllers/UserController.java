package com.example.springbootjm.controllers;


import com.example.springbootjm.dao.UserDao;
import com.example.springbootjm.models.User;
import com.example.springbootjm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDao userDao;

    @Autowired
    public UserController(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @GetMapping()
    public String listForAdmin(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserByName(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("authentication", authentication);
        return "users";
    }


}

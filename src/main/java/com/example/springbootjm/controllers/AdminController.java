package com.example.springbootjm.controllers;


import com.example.springbootjm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springbootjm.models.Role;
import com.example.springbootjm.models.User;


import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    @GetMapping("/")
    public String getAdmin() {
        return "admin";
    }

}

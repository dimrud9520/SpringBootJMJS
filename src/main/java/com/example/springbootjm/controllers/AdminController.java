package com.example.springbootjm.controllers;


import com.example.springbootjm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springbootjm.models.Role;
import com.example.springbootjm.models.User;


import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String listForUsers(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "redirect:/users";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(required = false, name  = "ADMIN") String ADMIN,
                          @RequestParam(required = false, name  = "USER") String USER) {
        Set<Role> roles = new HashSet<>();
        if(ADMIN != null){
            roles.add(new Role(1,ADMIN));
        }
        if(USER != null){
            roles.add(new Role(2, USER));
        }
        if(ADMIN == null && USER == null){
            roles.add(new Role(2, USER));
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.get(id));
        System.out.println("getMapping сработал");
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable(name = "id") int id,
                             @RequestParam(required = false, name  = "ADMIN") String ADMIN,
                             @RequestParam(required = false, name  = "USER") String USER) {
        System.out.println("postMapping сработал");
        Set<Role> roles = new HashSet<>();
        if(ADMIN != null){
            roles.add(new Role(1,ADMIN));
        }
        if(USER != null){
            roles.add(new Role(2, USER));
        }
        if(ADMIN == null && USER == null){
            roles.add(new Role(2, USER));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }



    @DeleteMapping(value = "/{id}")
    public String removeUserById(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}

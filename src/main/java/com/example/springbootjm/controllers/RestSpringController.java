package com.example.springbootjm.controllers;

import com.example.springbootjm.models.Role;
import com.example.springbootjm.models.User;
import com.example.springbootjm.service.RoleServiceImpl;
import com.example.springbootjm.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RestSpringController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public RestSpringController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/getAuthorizedUser")
    public ResponseEntity<?> getAuthorizedUser() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiceImpl.findByUsername(userDetails.getUsername());
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userServiceImpl.getAllUsers();
        System.out.println("метод сработал!");
        System.out.println(users);
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Iterable<Role>> getAllRoles() {

        return ResponseEntity.ok().body(roleServiceImpl.findAllByRole());
    }

    @GetMapping(value = "/getUserById/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") long id) {
        final User user = userServiceImpl.get(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userServiceImpl.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userServiceImpl.addUser(user);
        return ResponseEntity.ok().body(user);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        userServiceImpl.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
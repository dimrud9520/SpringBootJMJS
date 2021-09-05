package com.example.springbootjm.service;

import com.example.springbootjm.models.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> findAllByRole();

}
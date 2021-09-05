package com.example.springbootjm.service;

import com.example.springbootjm.models.Role;
import com.example.springbootjm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAllByRole() {
        var it = roleRepository.findAll();
        var roles = new HashSet<Role>();
        it.forEach(e -> roles.add(e));
        return roles;
    }
}
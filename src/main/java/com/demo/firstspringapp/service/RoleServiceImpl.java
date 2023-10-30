package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRoles(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findRoleByRoleName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public List<Role> getRolesByUser(int id) {
        return roleRepository.findRoleByUser(id);
    }
}

package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements com.demo.firstspringapp.service.RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void saveRoles(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role findRoleByRoleName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    @Transactional
    public List<Role> getRolesByUser(int id) {
        return roleRepository.findRoleByUser(id);
    }
}

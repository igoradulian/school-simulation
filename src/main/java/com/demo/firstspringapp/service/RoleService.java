package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.Role;

import java.util.List;

public interface RoleService {

    public void saveRoles(Role role);
    public Role findRoleByRoleName(String name);
    public List<Role> getRolesByUser(int id);

}

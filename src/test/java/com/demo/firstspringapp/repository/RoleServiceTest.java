package com.demo.firstspringapp.repository;

import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Igor Adulyan
 */

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    RoleService roleService;

    @Test
    public void testFindRoleByName()
    {
        Role roleExpected = new Role("ROLE_STUDENT");

        Role roleActual = roleService.findRoleByRoleName("ROLE_STUDENT");

        Assertions.assertEquals(roleExpected.getName(),roleActual.getName());
    }
}

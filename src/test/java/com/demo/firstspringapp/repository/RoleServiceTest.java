package com.demo.firstspringapp.repository;

import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.service.RoleService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Igor Adulyan
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleServiceTest {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @BeforeAll
    public void setup() {
       roleService.saveRoles(new Role("ROLE_STUDENT"));
       roleService.saveRoles(new Role("ROLE_PROFESSOR"));
    }

    @Test
    public void testFindRoleByName()
    {
       /* Role roleExpected = new Role("ROLE_STUDENT");

        Role roleActual = roleService.findRoleByRoleName("ROLE_STUDENT");

        Assertions.assertEquals(roleExpected.getName(),roleActual.getName());*/
    }

    @AfterAll
    public void cleanUp()
    {
        roleRepository.deleteAll();
    }
}

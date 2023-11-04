package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.entity.User;
import com.demo.firstspringapp.exception.UserExistException;
import com.demo.firstspringapp.model.UserDTO;
import com.demo.firstspringapp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.Arrays;

/**
 * @author Igor Adulyan
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @BeforeAll
    public void setup() {
        roleService.saveRoles(new Role("ROLE_STUDENT"));
        roleService.saveRoles(new Role("ROLE_PROFESSOR"));
    }

    @Test
    public void testSaveUser() throws UserExistException {
        User user = new User();
        user.setId(1);
        user.setEmail("john@test.com");
        user.setPassword("12345");
        user.setLogin("john");
        user.setRoles(Arrays.asList(new Role("ROLE_STUDENT")));

        userRepository.save(user);

        User actualUser = userService.findUserByEmail("john@test.com");
        Assertions.assertNotNull(actualUser.getEmail(), user.getEmail());

    }


}

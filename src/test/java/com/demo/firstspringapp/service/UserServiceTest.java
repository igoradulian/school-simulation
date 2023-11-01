package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.User;
import com.demo.firstspringapp.model.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Igor Adulyan
 */

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testSaveUser()
    {
        UserDTO user = new UserDTO();
        user.setEmail("john@test.com");
        user.setPassword("12345");
        user.setLogin("john");

        userService.saveUser(user);

        User actualUser = userService.findUserByEmail("john@test.com");
        Assertions.assertTrue(actualUser.getRoles().size() > 0);

    }
}

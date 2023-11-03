package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.User;
import com.demo.firstspringapp.exception.UserExistException;
import com.demo.firstspringapp.model.UserDTO;
import com.demo.firstspringapp.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author Igor Adulyan
 */

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    static UserRepository userRepository;

    @Test
    public void testSaveUser() throws UserExistException {
        UserDTO user = new UserDTO();
        user.setEmail("john@test.com");
        user.setPassword("12345");
        user.setLogin("john");
        user.setRole("ROLE_STUDENT");

        userService.saveUser(user);

        User actualUser = userService.findUserByEmail("john@test.com");
        Assertions.assertNotNull(actualUser.getEmail(), user.getEmail());

    }

    @AfterAll
    public static void clean(){

       // userRepository.delete( userRepository.findUserByEmail("john@test.com"));

    }

}

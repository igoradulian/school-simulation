package com.demo.firstspringapp.service;

import com.demo.firstspringapp.entity.User;
import com.demo.firstspringapp.exception.UserExistException;
import com.demo.firstspringapp.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String userName);

    public void saveUser(UserDTO user) throws UserExistException;

    public User findUserByEmail(String email);

    public User findUserByLogin(String login);
}

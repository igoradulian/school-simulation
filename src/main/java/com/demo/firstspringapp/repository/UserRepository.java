package com.demo.firstspringapp.repository;

import com.demo.firstspringapp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findUserByEmail(String email);
    public User findUserByLogin(String login);
}

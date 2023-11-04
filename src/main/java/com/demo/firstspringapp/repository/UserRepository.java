package com.demo.firstspringapp.repository;

import com.demo.firstspringapp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findUserByEmail(String email);
    public User findUserByLogin(String login);
}

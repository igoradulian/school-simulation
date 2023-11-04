package com.demo.firstspringapp.repository;

import com.demo.firstspringapp.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    public Role findRoleByName(String role);

    @Query(value = "select * from role where role.id=(select role_id from user_roles where user_id = :id)", nativeQuery = true)
    public List<Role> findRoleByUser(int id);

}

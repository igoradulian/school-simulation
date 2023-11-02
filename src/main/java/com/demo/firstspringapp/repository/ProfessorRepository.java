package com.demo.firstspringapp.repository;

import com.demo.firstspringapp.entity.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

    public Optional <Professor> findProfessorByEmail(String email);
}

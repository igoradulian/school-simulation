package com.demo.firstspringapp.repository;

import com.demo.firstspringapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Optional<Student> findStudentByEmail(String email);

    @Query(value = "from Student where lastName like %:name%")
    public List<Student> findStudentsByLastNameLike(@Param(value = "name") String name);

}

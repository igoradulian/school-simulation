package com.demo.firstspringapp.service;

import com.demo.firstspringapp.model.Student;

import java.util.List;

public interface StudentService {

    public void saveStudent(Student student);
    public List<Student> getAllStudents();
}

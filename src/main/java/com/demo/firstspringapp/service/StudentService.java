package com.demo.firstspringapp.service;

import com.demo.firstspringapp.model.AddressDTO;
import com.demo.firstspringapp.model.StudentDTO;

import java.util.List;

public interface StudentService {

    public void saveStudent(StudentDTO studentDTO, AddressDTO addressDTO);
    public List<StudentDTO> getAllStudents();

    public StudentDTO findStudentByEmail(String email);
}

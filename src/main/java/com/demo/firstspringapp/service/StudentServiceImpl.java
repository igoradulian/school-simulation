package com.demo.firstspringapp.service;

import com.demo.firstspringapp.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

    /**
     * This map is used now instead real database
     * just to keep things easy
     */
    private static final Map<String, Student> studentDB = new HashMap<>();

    @Override
    public void saveStudent(Student student) {

        studentDB.put(student.getEmail(), student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDB.values().stream().toList();
    }
}

package com.demo.firstspringapp.service;

import com.demo.firstspringapp.ConfigTest;
import com.demo.firstspringapp.entity.Student;
import com.demo.firstspringapp.model.AddressDTO;
import com.demo.firstspringapp.model.StudentDTO;
import com.demo.firstspringapp.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
@Import(ConfigTest.class)
public class StudentServiceTest {

    StudentDTO studentDTO;

    AddressDTO addressDTO;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ConfigTest configTest;

    @BeforeEach
    public void init()
    {
        studentDTO = new StudentDTO();
        studentDTO.setEmail("test@test.com");
        studentDTO.setEnabled("Yes");
        studentDTO.setFirstName("Igor");
        studentDTO.setLastName("Adulyan");

        addressDTO = new AddressDTO();
        addressDTO.setStreetName("123 Main st");

    }

    @Test
    @Order(1)
    public void saveStudent()
    {

        StudentDTO studentDTO1 = configTest.getTestStudentDTOObject();
        studentService.saveStudent(studentDTO, addressDTO);
        List<Student> studentList = (List<Student>) studentRepository.findAll();

       /* studentList.stream().forEach(s ->{
            Assertions.assertEquals("test@test.com", s.getEmail());
        });*/

        for(Student student : studentList)
        {
            Assertions.assertEquals("test@test.com", student.getEmail());
        }

    }

    @Test
    @Order(2)
    public void testStudentSearch()
    {
        String name = "Adul";
        List<StudentDTO> studentDTOList = studentService.searchStudentsByLastName(name);
        Assertions.assertTrue(studentDTOList.size() > 0);

        studentDTOList.stream().forEach(s ->{
            Assertions.assertEquals("test@test.com", s.getEmail());
        });

    }
}

package com.demo.firstspringapp.controller;

import com.demo.firstspringapp.model.Address;
import com.demo.firstspringapp.model.Student;
import com.demo.firstspringapp.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class.getName());
    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String showStudent(Model model)
    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.demo.firstspringapp.model");
        context.refresh();

        Student student =  context.getBean(Student.class);

        Address address = new Address();
        student.setAddress(address);
        student.setFirstName("Igor");
        student.setLastName("Adulyan");
        student.setEmail("test@test.com");

        model.addAttribute("student", student);

        logger.info("Student info is here " + student.getEmail());

        return "index";

    }


    @GetMapping("/form")
    public String studentForm(Model model)
    {
        model.addAttribute("student", new Student());

        return "student-sign-up";
    }

    @PostMapping("/process-student")
    public String processForm(@Valid @ModelAttribute ("student") Student student,
                              BindingResult bindingResult){

        if(bindingResult.hasErrors())
        {
            logger.warn("User wrong input " +
                    Arrays.toString(bindingResult.getSuppressedFields()));
            return "student-sign-up";
        }

        studentService.saveStudent(student);

        return "confirmation";
    }


    @GetMapping("/list-of-students")
    public String listOfStudents(Model model){

        model.addAttribute("students", studentService.getAllStudents());

        return "all-students";
    }


}

package com.demo.firstspringapp.controller;


import com.demo.firstspringapp.model.Professor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prof")
public class ProfessorController {


    @GetMapping("/")
    public String showProfessor()
    {
        populateProfessor();
        return "professor-account";
    }

    @ModelAttribute("professor")
    public Professor populateProfessor()
    {
        Professor professor = new Professor();
        professor.setFirstName("Jack");
        professor.setLastName("Smith");

        return professor;
    }

}
